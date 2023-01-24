package com.castle.weatherupdater;

import com.castle.data.repositories.IDataRepository;
import com.castle.weatherclient.IWeatherClient;
import com.castle.weatherupdater.mappers.HourlyWeatherElementMapper;
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
        var weatherDto = weatherClient.getCurrentWeather();

        var weatherDescription = catalogMapper
                .getWeatherDescriptionMapper()
                        .map(weatherDto.getCurrentWeatherDto().getWeatherDescriptionDto().get(0));
        var weather = catalogMapper
                .getWeatherMapper()
                        .map(weatherDto);

        weatherDescription.setWeather(weather);

        dataRepository
                .getWeatherRepository()
                .save(weather);

        dataRepository
                .getWeatherDescriptionRepository()
                .save(weatherDescription);

    }

    @Override
    public void updateDailyWeather() {
        var dailyWeatherDto = weatherClient
                .getDailyWeather();

        var dailyWeather = catalogMapper
                .getDailyWeatherMapper()
                        .map(dailyWeatherDto);

        dataRepository
                .getDailyWeatherRepository()
                        .save(dailyWeather);

        dailyWeatherDto
                .getDailyWeatherElementsDto()
                        .forEach((dailyWeatherElementDto) -> {
                            var dailyWeatherElement = catalogMapper
                                    .getDailyWeatherElementMapper()
                                    .map(dailyWeatherElementDto);

                            var weatherDescription = catalogMapper
                                    .getWeatherDescriptionMapper()
                                    .map(dailyWeatherElementDto.getWeatherDescription().get(0));

                            weatherDescription.setDailyWeatherElement(dailyWeatherElement);
                            dailyWeatherElement.setDailyWeather(dailyWeather);

                            dataRepository
                                    .getDailyWeatherElementRepository()
                                    .save(dailyWeatherElement);

                            dataRepository
                                    .getWeatherDescriptionRepository()
                                    .save(weatherDescription);
                        });

    }

    @Override
    public void updateHourlyWeather() {
        var hourlyWeatherDto = weatherClient.getHourlyWeather();

        var hourlyWeather = catalogMapper
                .getHourlyWeatherMapper()
                .map(hourlyWeatherDto);

        dataRepository
                .getHourlyWeatherRepository()
                .save(hourlyWeather);

        hourlyWeatherDto
                .getHourlyWeatherElementDtos()
                    .forEach((hourlyWeatherElementDto) -> {
                        var hourlyWeatherElement = catalogMapper
                                .getHourlyWeatherElementMapper()
                                .map(hourlyWeatherElementDto);

                        var weatherDescription = catalogMapper
                                .getWeatherDescriptionMapper()
                                .map(hourlyWeatherElementDto.getWeatherDescriptionDtos().get(0));

                        weatherDescription.setHourlyWeatherElement(hourlyWeatherElement);
                        hourlyWeatherElement.setHourlyWeather(hourlyWeather);

                        dataRepository
                                .getHourlyWeatherElementRepository()
                                .save(hourlyWeatherElement);

                        dataRepository
                                .getWeatherDescriptionRepository()
                                .save(weatherDescription);
                    });
    }
}
