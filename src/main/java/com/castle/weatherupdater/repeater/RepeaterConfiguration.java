package com.castle.weatherupdater.repeater;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepeaterConfiguration {

    @Bean
    public Repeater repeater() {
        return new Repeater();
    }

    @Bean
    public Task task() {
        return new Task();
    }
}
