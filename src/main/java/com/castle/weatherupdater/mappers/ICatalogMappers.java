package com.castle.weatherupdater.mappers;

import com.castle.data.model.*;
import com.castle.weatherclient.contract.*;

public interface ICatalogMappers {
    IMapEntities<DailyWeatherDto, DailyWeather> getDailyWeatherMapper();
    IMapEntities<DailyWeatherElementDto, DailyWeatherElement> getDailyWeatherElementMapper();
    IMapEntities<HourlyWeatherDto, HourlyWeather> getHourlyWeatherMapper();
    IMapEntities<HourlyWeatherElementDto, HourlyWeatherElement> getHourlyWeatherElementMapper();
    IMapEntities<WeatherDto, Weather> getWeatherMapper();
    IMapEntities<WeatherDescriptionDto, WeatherDescription> getWeatherDescriptionMapper();
}
