package com.castle.weatherupdater;

import com.castle.weatherupdater.repeater.Repeater;
import com.castle.weatherupdater.repeater.RepeaterThread;
import com.castle.weatherupdater.repeater.Task;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication(scanBasePackages = "com.castle")
@RequiredArgsConstructor
@Slf4j
public class CastleWeatherUpdaterApplication implements CommandLineRunner {
    private final WeatherUpdater weatherUpdater;

    public static void main(String[] args) {
        SpringApplication.run(CastleWeatherUpdaterApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Repeater updateCurrentWeatherRepeater = new Repeater(Duration.ofSeconds(10), LocalDateTime.now());
        Repeater updateDailyWeatherRepeater = new Repeater(Duration.ofSeconds(10), LocalDateTime.now());
        Repeater updateHourlyWeatherRepeater = new Repeater(Duration.ofSeconds(10), LocalDateTime.now());

        Task updateCurrentWeatherTask = new Task(weatherUpdater::updateCurrentWeather);
        Task updateDailyWeatherTask = new Task(weatherUpdater::updateDailyWeather);
        Task updateHourlyWeatherTask = new Task(weatherUpdater::updateHourlyWeather);

        RepeaterThread updateCurrentWeatherThread = new RepeaterThread(updateCurrentWeatherRepeater);
        RepeaterThread updateDailyWeatherThread = new RepeaterThread(updateDailyWeatherRepeater);
        RepeaterThread updateHourlyWeatherThread = new RepeaterThread(updateHourlyWeatherRepeater);

        updateCurrentWeatherTask.setName("currentWeatherUpdater");
        updateCurrentWeatherTask.setOnError((exception) -> log.error(exception.getMessage()));
        updateCurrentWeatherTask.setOnCompletedTask(() -> log.info("updateCurrentWeatherTask completed"));
        updateCurrentWeatherTask.setOnTerminatedTask(() -> log.info("updateCurrentWeatherTask terminated"));

        updateDailyWeatherTask.setName("updateDailyWeatherTask");
        updateDailyWeatherTask.setOnError((exception) -> log.error(exception.getMessage()));
        updateDailyWeatherTask.setOnCompletedTask(() -> log.info("updateDailyWeatherTask completed"));
        updateDailyWeatherTask.setOnTerminatedTask(() -> log.info("updateDailyWeatherTask terminated"));

        updateHourlyWeatherTask.setName("updateHourlyWeatherTask");
        updateHourlyWeatherTask.setOnError((exception) -> log.error(exception.getMessage()));
        updateHourlyWeatherTask.setOnCompletedTask(() -> log.info("updateHourlyWeatherTask completed"));
        updateHourlyWeatherTask.setOnTerminatedTask(() -> log.info("updateHourlyWeatherTask terminated"));

        updateCurrentWeatherRepeater.addTask(updateCurrentWeatherTask);
        updateDailyWeatherRepeater.addTask(updateDailyWeatherTask);
        updateHourlyWeatherRepeater.addTask(updateHourlyWeatherTask);

        new Thread(updateCurrentWeatherThread).start();
        new Thread(updateDailyWeatherThread).start();
        new Thread(updateHourlyWeatherThread).start();
    }
}
