package com.castle.weatherupdater.mappers;

import com.castle.data.model.HourlyWeather;
import com.castle.weatherclient.contract.HourlyWeatherDto;
import org.springframework.stereotype.Component;

@Component
public class HourlyWeatherMapper implements IMapEntities<HourlyWeatherDto, HourlyWeather> {

    @Override
    public HourlyWeather map(HourlyWeatherDto hourlyWeatherDto) {
        return map(new HourlyWeather(), hourlyWeatherDto);
    }

    @Override
    public HourlyWeather map(HourlyWeather hourlyWeather, HourlyWeatherDto hourlyWeatherDto) {
        return hourlyWeather;
    }
}
