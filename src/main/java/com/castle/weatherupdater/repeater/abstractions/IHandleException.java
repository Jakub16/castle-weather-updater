package com.castle.weatherupdater.repeater.abstractions;

@FunctionalInterface
public interface IHandleException {
    void handleException(Exception exception);
}
