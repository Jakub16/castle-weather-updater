package com.castle.weatherupdater.mappers;

import com.castle.data.model.WeatherDescription;
import com.castle.weatherclient.contract.WeatherDescriptionDto;

public class WeatherDescriptionMapper implements IMapper<WeatherDescriptionDto, WeatherDescription> {
    @Override
    public WeatherDescription map(WeatherDescriptionDto weatherDescriptionDto) {
        return map(new WeatherDescription(), weatherDescriptionDto);
    }

    @Override
    public WeatherDescription map(WeatherDescription weatherDescription, WeatherDescriptionDto weatherDescriptionDto) {
        weatherDescription.setMainDescription(weatherDescriptionDto.getMainDescription());
        weatherDescription.setDescription(weatherDescriptionDto.getDescription());
        weatherDescription.setIcon(weatherDescriptionDto.getIcon());

        return weatherDescription;
    }
}
