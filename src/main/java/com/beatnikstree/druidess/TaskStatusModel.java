package com.beatnikstree.druidess;

public class TaskStatusModel {

    private TaskStatusJson taskStatus;
    private Boolean isSelected = false;
    private String urlEncodedId;

    public TaskStatusModel(TaskStatusJson taskStatus, Boolean isSelected) {
        this.taskStatus = taskStatus;
        this.isSelected = isSelected;
    }

    public TaskStatusJson getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatusJson taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean selected) {
        isSelected = selected;
    }

    public String getUrlEncodedId() {
        return HelperUtils.encodeToUrlUtf8(taskStatus.getId());
    }
}
