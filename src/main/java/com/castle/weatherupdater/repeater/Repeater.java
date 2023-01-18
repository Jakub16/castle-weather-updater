package com.castle.weatherupdater.repeater;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Repeater {
    private List<Task> tasks = new ArrayList<>();
    private Duration timeInterval;
    private boolean isTerminated;
}
