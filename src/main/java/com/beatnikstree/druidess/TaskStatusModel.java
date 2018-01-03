package com.beatnikstree.druidess;

public class TaskStatusModel {

    private String id;
    private TaskStatusJson taskStatus;
    private Boolean isSelected = false;
    private String urlEncodedId;

    public TaskStatusModel() {
    }

    public TaskStatusModel(String id, TaskStatusJson taskStatus, Boolean isSelected) {
        this.id = id;
        this.taskStatus = taskStatus;
        this.isSelected = isSelected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
