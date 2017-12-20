package com.beatnikstree.druidess;

import org.junit.Test;

import java.util.List;

public class TaskServiceTest {

    TaskService taskService = new TaskService();

    @Test
    public void getCompletedTasksTest() {
        List<TaskStatusJson> taskStatuses = taskService.getCompleteTasks();
        System.out.println("Hope you have some tasks.");
    }

    @Test
    public void getRunningTasksTest() {
        List<TaskStatusJson> taskStatuses = taskService.getRunningTasks();
        System.out.println("Hope you have some tasks.");
    }

    @Test
    public void getWaitingTasksTest() {
        List<TaskStatusJson> taskStatuses = taskService.getWaitingTasks();
        System.out.println("Hope you have some tasks.");
    }

    @Test
    public void getPendingTasksTest() {
        List<TaskStatusJson> taskStatuses = taskService.getPendingTasks();
        System.out.println("Hope you have some tasks.");
    }
}
