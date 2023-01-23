package com.castle.weatherupdater;

import com.castle.data.repositories.IDataRepository;
import com.castle.weatherupdater.repeater.Repeater;
import com.castle.weatherupdater.repeater.RepeaterThread;
import com.castle.weatherupdater.repeater.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication(scanBasePackages = "com.castle")
@RequiredArgsConstructor
public class CastleWeatherUpdaterApplication implements CommandLineRunner {
    private WeatherUpdater weatherUpdater;
    private RepeaterThread repeaterThread;

    public static void main(String[] args) {
        SpringApplication.run(CastleWeatherUpdaterApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Repeater repeater = new Repeater(Duration.ofSeconds(60), LocalDateTime.now());
        Task task = new Task(weatherUpdater::updateCurrentWeather);

        task.setName("weatherUpdater");
        task.setOnTerminatedTask(() -> System.out.println("error"));
        task.setOnCompletedTask(() -> System.out.println("completed"));
        task.setOnTerminatedTask(() -> System.out.println("terminated"));

        repeater.addTask(task);

        new Thread(repeaterThread).start();
    }
}
