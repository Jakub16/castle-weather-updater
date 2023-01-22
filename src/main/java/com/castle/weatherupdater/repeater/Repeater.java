package com.castle.weatherupdater.repeater;

import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
public class Repeater {
    private List<Task> tasks = new ArrayList<>();
    private LocalDateTime startTime = LocalDateTime.now();
    private Duration timeInterval;

    public Repeater(Duration timeInterval, LocalDateTime startTime) {
        this.startTime = startTime;
        this.timeInterval = timeInterval;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public Task getTaskByName(String name) {
        return getTasks()
                .stream()
                .filter((task) -> Objects.equals(task.getName(), name))
                .findFirst()
                .orElse(null);
    }

    public void removeTaskByName(String name) {
        this.tasks
                .remove(getTaskByName(name));
    }

    public void setTimeInterval(Duration timeInterval) {
        this.timeInterval = timeInterval;
    }

    public long getTimeInterval() {
        return this.timeInterval.toMillis();
    }
}
