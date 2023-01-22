package com.castle.weatherupdater.mappers;

import com.castle.data.model.WeatherDescription;
import com.castle.weatherclient.contract.WeatherDescriptionDto;
import org.springframework.stereotype.Component;

@Component
public class WeatherDescriptionMapper {

    public WeatherDescription map(WeatherDescriptionDto weatherDescriptionDto) {
        return map(new WeatherDescription(), weatherDescriptionDto);
    }

    public WeatherDescription map(WeatherDescription weatherDescription, WeatherDescriptionDto weatherDescriptionDto) {
        weatherDescription.setMainDescription(weatherDescriptionDto.getMainDescription());
        weatherDescription.setDescription(weatherDescriptionDto.getDescription());
        weatherDescription.setIcon(weatherDescriptionDto.getIcon());

        return weatherDescription;
    }
}
