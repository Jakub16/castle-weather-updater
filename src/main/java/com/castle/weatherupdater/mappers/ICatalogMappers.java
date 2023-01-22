package com.castle.weatherupdater.mappers;

import com.castle.data.model.DailyWeather;
import com.castle.data.model.HourlyWeather;
import com.castle.data.model.Weather;
import com.castle.weatherclient.contract.DailyWeatherDto;
import com.castle.weatherclient.contract.HourlyWeatherDto;
import com.castle.weatherclient.contract.WeatherDto;

public interface ICatalogMappers {
    IMapEntities<DailyWeatherDto, DailyWeather, DailyWeatherElementMapper> getDailyWeatherMapper();
    IMapEntities<HourlyWeatherDto, HourlyWeather, HourlyWeatherElementMapper> getHourlyWeatherMapper();
    IMapEntities<WeatherDto, Weather, WeatherDescriptionMapper> getWeatherMapper();
}
