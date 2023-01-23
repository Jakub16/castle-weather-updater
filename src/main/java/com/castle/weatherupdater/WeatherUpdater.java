package com.castle.weatherupdater;

import com.castle.data.repositories.IDataRepository;
import com.castle.weatherclient.IWeatherClient;
import com.castle.weatherupdater.mappers.ICatalogMappers;
import com.castle.weatherupdater.repeater.abstractions.IUpdateWeather;
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

        dataRepository
                .getWeatherRepository()
                .save(
                        catalogMapper
                                .getWeatherMapper()
                                .map(weatherClient.getCurrentWeather())
                );
    }

    @Override
    public void updateDailyWeather() {

        dataRepository
                .getDailyWeatherRepository()
                .save(
                        catalogMapper
                                .getDailyWeatherMapper()
                                .map(weatherClient.getDailyWeather())
                );
    }

    @Override
    public void updateHourlyWeather() {

        dataRepository
                .getHourlyWeatherRepository()
                .save(
                        catalogMapper
                                .getHourlyWeatherMapper()
                                .map(weatherClient.getHourlyWeather())
                );
    }
}
