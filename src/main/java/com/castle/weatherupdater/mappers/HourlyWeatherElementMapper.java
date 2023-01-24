package com.castle.weatherupdater.mappers;

import com.castle.data.model.HourlyWeatherElement;
import com.castle.weatherclient.contract.HourlyWeatherElementDto;
import org.springframework.stereotype.Component;

@Component
public class HourlyWeatherElementMapper implements IMapEntities<HourlyWeatherElementDto, HourlyWeatherElement> {

    @Override
    public HourlyWeatherElement map(HourlyWeatherElementDto hourlyWeatherElementDto) {
        return map(new HourlyWeatherElement(), hourlyWeatherElementDto);
    }

    @Override
    public HourlyWeatherElement map(HourlyWeatherElement hourlyWeatherElement, HourlyWeatherElementDto hourlyWeatherElementDto) {
        hourlyWeatherElement.setUnixTime(hourlyWeatherElementDto.getUnixTime());
        hourlyWeatherElement.setTemperature(hourlyWeatherElementDto.getTemperature());
        hourlyWeatherElement.setPerceivedTemperature(hourlyWeatherElementDto.getTemperature());
        hourlyWeatherElement.setPressure(hourlyWeatherElementDto.getPressure());
        hourlyWeatherElement.setHumidity(hourlyWeatherElementDto.getHumidity());
        hourlyWeatherElement.setUvi(hourlyWeatherElementDto.getUvi());
        hourlyWeatherElement.setCloudiness(hourlyWeatherElementDto.getCloudiness());
        hourlyWeatherElement.setWindSpeed(hourlyWeatherElementDto.getWindSpeed());

        return hourlyWeatherElement;
    }
}
