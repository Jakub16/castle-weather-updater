package com.castle.weatherupdater;

import com.castle.data.repositories.IDataRepository;
import com.castle.weatherclient.IWeatherClient;
import com.castle.weatherupdater.mappers.ICatalogMappers;
import com.castle.weatherupdater.repeater.IUpdateWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class WeatherUpdater implements IUpdateWeather {
    private final IDataRepository dataRepository;
    private final IWeatherClient weatherClient;
    private final ICatalogMappers catalogMapper;

    public WeatherUpdater(IDataRepository dataRepository,
                          IWeatherClient weatherClient,
                          ICatalogMappers catalogMapper) {
        this.dataRepository = dataRepository;
        this.weatherClient = weatherClient;
        this.catalogMapper = catalogMapper;
    }

    @Override
    public void updateCurrentWeather() {
        var weatherDto = weatherClient.getCurrentWeather();

        dataRepository
                .getWeatherRepository()
                .save(
                        catalogMapper
                                .getWeatherMapper()
                                .map(weatherDto)
                );
    }

    @Override
    public void updateDailyWeather() {
        var dailyWeatherDto = weatherClient.getDailyWeather();

        dataRepository
                .getDailyWeatherRepository()
                .save(
                        catalogMapper
                                .getDailyWeatherMapper()
                                .map(dailyWeatherDto)
                );
    }

    @Override
    public void updateHourlyWeather() {
        var hourlyWeather = weatherClient.getHourlyWeather();

        dataRepository
                .getHourlyWeatherRepository()
                .save(
                        catalogMapper
                                .getHourlyWeatherMapper()
                                .map(hourlyWeather)
                );
    }
}
