package com.castle.weatherupdater.repeater.abstractions;

@FunctionalInterface
public interface IExceptionHandler {
    void handleException(Exception exception);
}
