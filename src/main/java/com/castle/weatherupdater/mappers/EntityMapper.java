package com.castle.weatherupdater.mappers;

import com.castle.data.model.DailyWeather;
import com.castle.data.model.HourlyWeather;
import com.castle.data.model.Weather;
import com.castle.weatherclient.contract.DailyWeatherDto;
import com.castle.weatherclient.contract.HourlyWeatherDto;
import com.castle.weatherclient.contract.WeatherDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor
public class EntityMapper implements ICatalogMappers {
    private final IMapEntities<DailyWeatherDto, DailyWeather, DailyWeatherElementMapper> dailyWeatherMapper;
    private final IMapEntities<HourlyWeatherDto, HourlyWeather, HourlyWeatherElementMapper> hourlyWeatherMapper;
    private final IMapEntities<WeatherDto, Weather, WeatherDescriptionMapper> weatherMapper;
}
