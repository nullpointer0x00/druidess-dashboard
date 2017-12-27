package com.beatnikstree.druidess;

import io.druid.indexing.common.TaskLocation;
import io.druid.indexing.common.TaskStatus;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

public class TaskStatusJson implements Serializable {

    private String id;
    private TaskStatus.Status statusCode;
    private Integer duration;
    private TaskLocation location;
    private Date createTime;
    private Date queueInsertionTime;

    public TaskStatusJson() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TaskStatus.Status getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(TaskStatus.Status statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public TaskLocation getLocation() {
        return location;
    }

    public void setLocation(TaskLocation location) {
        this.location = location;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getQueueInsertionTime() {
        return queueInsertionTime;
    }

    public void setQueueInsertionTime(Date queueInsertionTime) {
        this.queueInsertionTime = queueInsertionTime;
    }


    @Override
    public String toString() {
        return "TaskStatusJson{" +
                "id='" + id + '\'' +
                ", statusCode=" + statusCode +
                ", duration=" + duration +
                ", location=" + location +
                ", createTime=" + createTime +
                ", queueInsertionTime=" + queueInsertionTime +
                '}';
    }
}
