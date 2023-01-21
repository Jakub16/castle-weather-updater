package com.castle.weatherupdater;

import com.castle.weatherupdater.repeater.Repeater;
import com.castle.weatherupdater.repeater.Task;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Duration;
import java.time.LocalDateTime;

@RunWith(MockitoJUnitRunner.class)
public class RepeaterActionThrowingExceptionTests {

    @Mock
    Status status;
    @Mock
    Task errorTask;
    @InjectMocks
    Repeater repeater;

    @Before
    public void init() {
        status = new Status();

        errorTask = new Task(() -> {
            throw new Exception("exception occurred");
        });
        errorTask.setName("errorTask");
        errorTask.setOnError((e) -> status.setStatus(e.getMessage()));

        repeater = new Repeater(Duration.ofSeconds(1), LocalDateTime.now());
        repeater.addTask(errorTask);
    }

    @Test
    public void executingErrorTaskActionShouldCauseOnErrorAction() {
        repeater
                .getTasks()
                .forEach(Task::execute);

        Assert.assertEquals("exception occurred", status.getStatus());
    }
}
