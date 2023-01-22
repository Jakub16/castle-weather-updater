package com.castle.weatherupdater.mappers;
import com.castle.data.model.Weather;
import com.castle.weatherclient.contract.WeatherDto;
import org.springframework.stereotype.Component;

@Component
public class WeatherMapper implements IMapEntities<WeatherDto, Weather, WeatherDescriptionMapper> {

    @Override
    public Weather map(WeatherDto weatherDto) {
        return map(new Weather(), weatherDto, new WeatherDescriptionMapper());
    }

    @Override
    public Weather map(Weather weather, WeatherDto weatherDto, WeatherDescriptionMapper weatherDescriptionMapper) {
        weather.setLatitude(weatherDto.getLatitude());
        weather.setLongitude(weatherDto.getLongitude());
        weather.setUnixTime(weatherDto.getCurrentWeatherDto().getUnixTime());
        weather.setSunrise(weatherDto.getCurrentWeatherDto().getSunrise());
        weather.setSunset(weatherDto.getCurrentWeatherDto().getSunset());
        weather.setTemperature(weatherDto.getCurrentWeatherDto().getTemperature());
        weather.setPerceivedTemperature(weatherDto.getCurrentWeatherDto().getPerceivedTemperature());
        weather.setPressure(weatherDto.getCurrentWeatherDto().getPressure());
        weather.setHumidity(weatherDto.getCurrentWeatherDto().getHumidity());
        weather.setCloudiness(weatherDto.getCurrentWeatherDto().getCloudiness());
        weather.setWindSpeed(weatherDto.getCurrentWeatherDto().getWindSpeed());
        weather.setWeatherDescription(weatherDescriptionMapper.map(weatherDto.getCurrentWeatherDto().getWeatherDescriptionDto().get(0)));

        return weather;
    }
}
