package com.castle.weatherupdater;

import com.castle.weatherupdater.repeater.Repeater;
import com.castle.weatherupdater.repeater.RepeaterThread;
import com.castle.weatherupdater.repeater.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication(scanBasePackages = "com.castle")
@RequiredArgsConstructor
public class CastleWeatherUpdaterApplication implements CommandLineRunner {
    private final WeatherUpdater weatherUpdater;

    public static void main(String[] args) {
        SpringApplication.run(CastleWeatherUpdaterApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Repeater updateCurrentWeatherRepeater = new Repeater(Duration.ofSeconds(60), LocalDateTime.now());
        Repeater updateDailyWeatherRepeater = new Repeater(Duration.ofSeconds(120), LocalDateTime.now());
        Repeater updateHourlyWeatherRepeater = new Repeater(Duration.ofSeconds(180), LocalDateTime.now());

        Task updateCurrentWeatherTask = new Task(weatherUpdater::updateCurrentWeather);
        Task updateDailyWeatherTask = new Task(weatherUpdater::updateCurrentWeather);
        Task updateHourlyWeatherTask = new Task(weatherUpdater::updateHourlyWeather);

        RepeaterThread updateCurrentWeatherThread = new RepeaterThread(updateCurrentWeatherRepeater);
        RepeaterThread updateDailyWeatherThread = new RepeaterThread(updateDailyWeatherRepeater);
        RepeaterThread updateHourlyWeatherThread = new RepeaterThread(updateHourlyWeatherRepeater);

        updateCurrentWeatherTask.setName("currentWeatherUpdater");
        updateCurrentWeatherTask.setOnTerminatedTask(() -> System.out.println("updateCurrentWeatherTask error"));
        updateCurrentWeatherTask.setOnCompletedTask(() -> System.out.println("updateCurrentWeatherTask completed"));
        updateCurrentWeatherTask.setOnTerminatedTask(() -> System.out.println("updateCurrentWeatherTask terminated"));

        updateDailyWeatherTask.setName("updateDailyWeatherTask");
        updateDailyWeatherTask.setOnTerminatedTask(() -> System.out.println("updateDailyWeatherTask error"));
        updateDailyWeatherTask.setOnCompletedTask(() -> System.out.println("updateDailyWeatherTask completed"));
        updateDailyWeatherTask.setOnTerminatedTask(() -> System.out.println("updateDailyWeatherTask terminated"));

        updateHourlyWeatherTask.setName("updateHourlyWeatherTask");
        updateHourlyWeatherTask.setOnTerminatedTask(() -> System.out.println("updateHourlyWeatherTask error"));
        updateHourlyWeatherTask.setOnCompletedTask(() -> System.out.println("updateHourlyWeatherTask completed"));
        updateHourlyWeatherTask.setOnTerminatedTask(() -> System.out.println("updateHourlyWeatherTask terminated"));

        updateCurrentWeatherRepeater.addTask(updateCurrentWeatherTask);
        updateDailyWeatherRepeater.addTask(updateDailyWeatherTask);
        updateHourlyWeatherRepeater.addTask(updateHourlyWeatherTask);

        new Thread(updateCurrentWeatherThread).start();
        new Thread(updateDailyWeatherThread).start();
        new Thread(updateHourlyWeatherThread).start();
    }
}
