package com.castle.weatherupdater.repeater;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.LocalDateTime;

@Configuration
public class RepeaterConfiguration {

    @Bean
    public Repeater repeater() {
        return new Repeater(Duration.ofSeconds(60), LocalDateTime.now());
    }

    @Bean
    public Task task() {
        return new Task();
    }
}
