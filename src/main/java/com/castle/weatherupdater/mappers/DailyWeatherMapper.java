package com.castle.weatherupdater.mappers;

import com.castle.data.model.DailyWeather;
import com.castle.weatherclient.contract.DailyWeatherDto;
import org.springframework.stereotype.Component;

@Component
public class DailyWeatherMapper implements IMapEntities<DailyWeatherDto, DailyWeather, DailyWeatherElementMapper> {

    @Override
    public DailyWeather map(DailyWeatherDto dailyWeatherDto) {
        return map(new DailyWeather(), dailyWeatherDto, new DailyWeatherElementMapper());
    }

    @Override
    public DailyWeather map(DailyWeather dailyWeather, DailyWeatherDto dailyWeatherDto, DailyWeatherElementMapper dailyWeatherElementMapper) {
        dailyWeather.setDailyWeatherElements(
                dailyWeatherDto
                        .getDailyWeatherElements()
                        .stream()
                        .map((dailyWeatherElementDto) -> dailyWeatherElementMapper.map(dailyWeatherElementDto))
                        .toList()
        );

        return dailyWeather;
    }
}
