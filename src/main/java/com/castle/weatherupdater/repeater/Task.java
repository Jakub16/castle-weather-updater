package com.castle.weatherupdater.repeater;

import com.castle.weatherupdater.repeater.abstractions.IActionOnCompletedTask;
import com.castle.weatherupdater.repeater.abstractions.IExceptionHandler;

public class Task {
    private IActionOnCompletedTask onCompletedTask = () -> {};
    private IExceptionHandler onError = (exception) -> {};

    public void execute() {

    }
}
