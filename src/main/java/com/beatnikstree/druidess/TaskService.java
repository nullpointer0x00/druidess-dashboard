package com.beatnikstree.druidess;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TaskService {

    private static RestTemplate restTemplate = new RestTemplate();

    public final String v1IndexerEndpoint = "/druid/indexer/v1/";

    public final String waitingTasksEndpoint = "waitingTasks";
    public final String runningTasksEndpoint = "runningTasks";
    public final String completeTasksEndpoint = "completeTasks";
    public final String pendingTasksEndpoint = "pendingTasks";

    public List<TaskStatusJson> getCompleteTasks(){
        return getTasks("" + v1IndexerEndpoint + completeTasksEndpoint);
    }

    public List<TaskStatusJson> getRunningTasks(){
        return getTasks("" + v1IndexerEndpoint + runningTasksEndpoint);
    }

    public List<TaskStatusJson> getWaitingTasks(){
        return getTasks("" + v1IndexerEndpoint + waitingTasksEndpoint);
    }

    public List<TaskStatusJson> getPendingTasks(){
        return getTasks("" + v1IndexerEndpoint + pendingTasksEndpoint);
    }

    private List<TaskStatusJson> getTasks(String url) {
        ResponseEntity<TaskStatusJson[]> responseEntity = restTemplate.getForEntity(url, TaskStatusJson[].class);
        TaskStatusJson[] tasks = responseEntity.getBody();
        return Arrays.asList(tasks);
    }
}
