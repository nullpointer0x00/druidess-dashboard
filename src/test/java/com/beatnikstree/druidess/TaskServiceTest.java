package com.beatnikstree.druidess;

import org.junit.Test;

import java.util.List;

public class TaskServiceTest {

    String overlordUrl = "";
    TaskService taskService = new TaskService();

    @Test
    public void getCompletedTasksTest() {
        List<TaskStatusJson> taskStatuses = taskService.getCompleteTasks(overlordUrl);
        System.out.println("Hope you have some tasks.");
    }

    @Test
    public void getRunningTasksTest() {
        List<TaskStatusJson> taskStatuses = taskService.getRunningTasks(overlordUrl);
        System.out.println("Hope you have some tasks.");
    }

    @Test
    public void getWaitingTasksTest() {
        List<TaskStatusJson> taskStatuses = taskService.getWaitingTasks(overlordUrl);
        System.out.println("Hope you have some tasks.");
    }

    @Test
    public void getPendingTasksTest() {
        List<TaskStatusJson> taskStatuses = taskService.getPendingTasks(overlordUrl);
        System.out.println("Hope you have some tasks.");
    }
}
