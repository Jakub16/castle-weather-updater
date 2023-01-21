package com.castle.weatherupdater.repeater;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RepeaterThread implements Runnable {

    private final Repeater repeater; //remember to call getTaskExecution() when creating the repeater instance

    @Override
    public void run() {
        while(true) {
            repeater
                    .getTasks()
                    .forEach(Task::execute);
            try {
                Thread.sleep(repeater.getTimeInterval());
            } catch (InterruptedException interruptedException) {
                throw new RuntimeException(interruptedException);
            }
        }
    }
}
