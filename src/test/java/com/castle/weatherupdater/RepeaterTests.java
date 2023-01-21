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
public class RepeaterTests {

    @Mock
    Status status;
    @Mock
    Task task;
    @Mock
    Task anotherTask;
    @InjectMocks
    Repeater repeater;

    @Before
    public void configuration() {
        status = new Status();

        anotherTask = new Task(() -> status.setStatus("another task created"));
        anotherTask.setName("anotherTask");

        task = new Task(() -> status.setStatus("task created"));
        task.setName("updater");
        task.setOnCompletedTask(() -> status.setStatus("task completed"));
        task.setOnTerminatedTask(() -> status.setStatus("task terminated"));

        repeater = new Repeater(Duration.ofSeconds(1), LocalDateTime.now());
        repeater.addTask(anotherTask);
        repeater.addTask(task);
    }

    @Test
    public void taskNameShouldBeUpdater() {
        Assert.assertEquals("updater", repeater.getTaskByName("updater").getName());
    }

    @Test
    public void anotherTaskNameShouldBeAnotherTask() {
        Assert.assertEquals("anotherTask", repeater.getTaskByName("anotherTask").getName());
    }

    @Test
    public void sizeOfTasksListShouldBe2() {
        Assert.assertEquals(2, repeater.getTasks().size());
    }

    @Test
    public void executeTaskShouldMakeActionOnCompletedTask() {
        repeater
                .getTasks()
                .forEach(Task::execute);

        Assert.assertEquals("task completed", status.getStatus());
    }

    @Test
    public void repeaterShouldNotBeTerminatedOnDefault() {
        Assert.assertFalse(repeater.getTaskByName("updater").isTerminated());
    }

    @Test
    public void repeaterShouldBeTerminatedAndShouldMakeActionAfterTerminating() {
        repeater
                .getTaskByName("updater")
                .setTerminated(true);
        repeater
                .getTasks()
                .forEach(Task::execute);

        Assert.assertTrue(repeater
                .getTaskByName("updater")
                .isTerminated());
        Assert.assertEquals("task terminated", status.getStatus());
    }

    @Test(expected = NullPointerException.class)
    public void repeaterTaskShouldBeRemovedFromUpdaterAfterRemovingIt() {
        repeater.removeTaskByName("anotherTask");

        Assert.assertEquals("anotherTask", repeater.getTaskByName("anotherTask").getName());
    }
}
