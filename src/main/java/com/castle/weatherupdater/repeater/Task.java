package com.castle.weatherupdater.repeater;

import com.castle.weatherupdater.repeater.abstractions.IExecuteAction;
import com.castle.weatherupdater.repeater.abstractions.IProvideActionOnCompletedTask;
import com.castle.weatherupdater.repeater.abstractions.IHandleException;

public class Task {
    private String name;
    private IProvideActionOnCompletedTask onCompletedTask = () -> {};
    private IProvideActionOnCompletedTask onTerminatedTask = () -> {};
    private IHandleException onError = (exception) -> {};
    private IExecuteAction action = () -> {};
    private boolean isTerminated = false;

    public Task(IExecuteAction action) {
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOnCompletedTask(IProvideActionOnCompletedTask onCompletedTask) {
        this.onCompletedTask = onCompletedTask;
    }

    public void setOnTerminatedTask(IProvideActionOnCompletedTask onTerminatedTask) {
        this.onTerminatedTask = onTerminatedTask;
    }

    public void setOnError(IHandleException onError) {
        this.onError = onError;
    }

    public void setTerminated(boolean isTerminated) {
        this.isTerminated = isTerminated;
    }

    public boolean isTerminated() {
        return this.isTerminated;
    }

    public void execute() {
        if(isTerminated()) {
            this.onTerminatedTask.complete();
            return;
        } //and log that action was not completed due to isTerminated set to true
        try {
            this.action.executeAction();
            this.onCompletedTask.complete();
        } catch (Exception exception) {
            this.onError.handleException(exception);
        }
    }
}
