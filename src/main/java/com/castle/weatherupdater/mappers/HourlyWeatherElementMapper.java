package com.castle.weatherupdater.mappers;

import com.castle.data.model.HourlyWeatherElement;
import com.castle.weatherclient.contract.HourlyWeatherElementDto;
import org.springframework.stereotype.Component;

@Component
public class HourlyWeatherElementMapper implements IMapEntities<HourlyWeatherElementDto, HourlyWeatherElement, WeatherDescriptionMapper> {

    @Override
    public HourlyWeatherElement map(HourlyWeatherElementDto hourlyWeatherElementDto) {
        return map(new HourlyWeatherElement(), hourlyWeatherElementDto, new WeatherDescriptionMapper());
    }

    @Override
    public HourlyWeatherElement map(HourlyWeatherElement hourlyWeatherElement, HourlyWeatherElementDto hourlyWeatherElementDto, WeatherDescriptionMapper weatherDescriptionMapper) {
        hourlyWeatherElement.setUnixTime(hourlyWeatherElementDto.getUnixTime());
        hourlyWeatherElement.setTemperature(hourlyWeatherElementDto.getTemperature());
        hourlyWeatherElement.setPerceivedTemperature(hourlyWeatherElementDto.getTemperature());
        hourlyWeatherElement.setPressure(hourlyWeatherElementDto.getPressure());
        hourlyWeatherElement.setHumidity(hourlyWeatherElementDto.getHumidity());
        hourlyWeatherElement.setUvi(hourlyWeatherElementDto.getUvi());
        hourlyWeatherElement.setCloudiness(hourlyWeatherElementDto.getCloudiness());
        hourlyWeatherElement.setWindSpeed(hourlyWeatherElementDto.getWindSpeed());
        hourlyWeatherElement.setWeatherDescription(weatherDescriptionMapper.map(hourlyWeatherElementDto.getWeatherDescription().get(0)));

        return hourlyWeatherElement;
    }
}
