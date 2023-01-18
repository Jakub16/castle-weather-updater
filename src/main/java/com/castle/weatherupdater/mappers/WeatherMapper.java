package com.castle.weatherupdater.mappers;
import com.castle.data.model.Weather;
import com.castle.weatherclient.contract.WeatherDto;

public class WeatherMapper implements IMapper<WeatherDto, Weather> {

    @Override
    public Weather map(WeatherDto weatherDto) {
        return map(new Weather(), weatherDto);
    }

    @Override
    public Weather map(Weather weather, WeatherDto weatherDto) {
        weather.setCloudiness(weatherDto.getCloudiness());
        weather.setHumidity(weatherDto.getHumidity());
        weather.setLongitude(weatherDto.getLongitude());
        weather.setLongitude(weatherDto.getLongitude());
        weather.setHumidity(weatherDto.getHumidity());
        weather.setTemperature(weatherDto.getTemperature());
        weather.setPerceivedTemperature(weatherDto.getPerceivedTemperature());
        weather.setPressure(weatherDto.getPressure());
        weather.setSunrise(weatherDto.getSunrise());
        weather.setSunset(weatherDto.getSunset());
        weather.setUnixTime(weatherDto.getUnixTime());
        weather.setWindSpeed(weatherDto.getWindSpeed());

        return weather;
    }
}
