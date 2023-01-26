package com.castle.weatherupdater.mappers;

import com.castle.data.model.*;
import com.castle.weatherclient.contract.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor
public class EntityMapper implements ICatalogMappers {
    private final IMapEntities<DailyWeatherElementDto, DailyWeatherElement> dailyWeatherElementMapper;
    private final IMapEntities<HourlyWeatherElementDto, HourlyWeatherElement> hourlyWeatherElementMapper;
    private final IMapEntities<WeatherDto, Weather> weatherMapper;
    private final IMapEntities<WeatherDescriptionDto, WeatherDescription> weatherDescriptionMapper;

}
