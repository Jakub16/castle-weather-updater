package com.castle.weatherupdater.mappers;

import com.castle.data.model.DailyWeatherElement;
import com.castle.weatherclient.contract.DailyWeatherElementDto;
import org.springframework.stereotype.Component;

@Component
public class DailyWeatherElementMapper implements IMapEntities<DailyWeatherElementDto, DailyWeatherElement, WeatherDescriptionMapper> {

    @Override
    public DailyWeatherElement map(DailyWeatherElementDto dailyWeatherElementDto) {
        return map(new DailyWeatherElement(), dailyWeatherElementDto, new WeatherDescriptionMapper());
    }

    @Override
    public DailyWeatherElement map(DailyWeatherElement dailyWeatherElement, DailyWeatherElementDto dailyWeatherElementDto, WeatherDescriptionMapper weatherDescriptionMapper) {
        dailyWeatherElement.setUnixTime(dailyWeatherElementDto.getUnixTime());
        dailyWeatherElement.setSunrise(dailyWeatherElementDto.getSunrise());
        dailyWeatherElement.setSunset(dailyWeatherElementDto.getSunset());
        dailyWeatherElement.setDayTemperature(dailyWeatherElementDto.getDailyTemperatureDto().getDayTemperature());
        dailyWeatherElement.setMinTemperature(dailyWeatherElementDto.getDailyTemperatureDto().getMinTemperature());
        dailyWeatherElement.setMaxTemperature(dailyWeatherElementDto.getDailyTemperatureDto().getMaxTemperature());
        dailyWeatherElement.setNightTemperature(dailyWeatherElementDto.getDailyTemperatureDto().getNightTemperature());
        dailyWeatherElement.setEveningTemperature(dailyWeatherElementDto.getDailyTemperatureDto().getEveningTemperature());
        dailyWeatherElement.setMorningTemperature(dailyWeatherElementDto.getDailyTemperatureDto().getMorningTemperature());
        dailyWeatherElement.setDayPerceivedTemperature(dailyWeatherElementDto.getDailyFeelsLikeDto().getDayPerceivedTemperature());
        dailyWeatherElement.setNightPerceivedTemperature(dailyWeatherElementDto.getDailyFeelsLikeDto().getNightPerceivedTemperature());
        dailyWeatherElement.setEveningPerceivedTemperature(dailyWeatherElementDto.getDailyFeelsLikeDto().getEveningPerceivedTemperature());
        dailyWeatherElement.setMorningPerceivedTemperature(dailyWeatherElementDto.getDailyFeelsLikeDto().getMorningPerceivedTemperature());
        dailyWeatherElement.setPressure(dailyWeatherElementDto.getPressure());
        dailyWeatherElement.setHumidity(dailyWeatherElementDto.getHumidity());
        dailyWeatherElement.setWeatherDescription(weatherDescriptionMapper.map(dailyWeatherElementDto.getWeatherDescription().get(0)));
        dailyWeatherElement.setCloudiness(dailyWeatherElementDto.getCloudiness());
        dailyWeatherElement.setProbabilityOfPrecipitation(dailyWeatherElementDto.getProbabilityOfPrecipitation());
        dailyWeatherElement.setRainAmount(dailyWeatherElementDto.getRainAmount());
        dailyWeatherElement.setUvi(dailyWeatherElementDto.getUvi());

        return dailyWeatherElement;
    }
}
