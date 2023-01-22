package com.castle.weatherupdater;

import com.castle.weatherupdater.repeater.Repeater;
import com.castle.weatherupdater.repeater.RepeaterThread;
import com.castle.weatherupdater.repeater.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.Duration;
import java.time.LocalDateTime;

@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = "com.castle")
@RequiredArgsConstructor
public class CastleWeatherUpdaterApplication implements CommandLineRunner {

    private WeatherUpdater weatherUpdater;
    private Repeater repeater;
    private Task task;
    private RepeaterThread repeaterThread;

    public static void main(String[] args) {
        SpringApplication.run(CastleWeatherUpdaterApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repeater.setTimeInterval(Duration.ofSeconds(60));

        task.setName("weatherUpdater");
        task.setAction(weatherUpdater::updateCurrentWeather);
        task.setOnTerminatedTask(() -> System.out.println("error"));
        task.setOnCompletedTask(() -> System.out.println("completed"));
        task.setOnTerminatedTask(() -> System.out.println("terminated"));

        new Thread(repeaterThread).start();
    }
}
