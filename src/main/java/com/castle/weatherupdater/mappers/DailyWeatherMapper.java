package com.castle.weatherupdater.mappers;

import com.castle.data.model.DailyWeather;
import com.castle.weatherclient.contract.DailyWeatherDto;
import org.springframework.stereotype.Component;

@Component
public class DailyWeatherMapper implements IMapEntities<DailyWeatherDto, DailyWeather> {

    @Override
    public DailyWeather map(DailyWeatherDto dailyWeatherDto) {
        return map(new DailyWeather(), dailyWeatherDto);
    }

    @Override
    public DailyWeather map(DailyWeather dailyWeather, DailyWeatherDto dailyWeatherDto) {
        return dailyWeather;
    }
}
