package com.beatnikstree.druidess;

import java.util.List;

public class TaskFormData {
    public List<TaskStatusModel> taskStatusModels;

    public TaskFormData() {
    }

    public TaskFormData(List<TaskStatusModel> taskStatusModels) {
        this.taskStatusModels = taskStatusModels;
    }

    public List<TaskStatusModel> getTaskStatusModels() {
        return taskStatusModels;
    }

    public void setTaskStatusModels(List<TaskStatusModel> taskStatusModels) {
        this.taskStatusModels = taskStatusModels;
    }
}
