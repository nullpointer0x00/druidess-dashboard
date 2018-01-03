package com.beatnikstree.druidess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskService.class);

    private static RestTemplate restTemplate = new RestTemplate();

    public final String v1IndexerEndpoint = "/druid/indexer/v1/";

    public final String waitingTasksEndpoint = "waitingTasks";
    public final String runningTasksEndpoint = "runningTasks";
    public final String completeTasksEndpoint = "completeTasks";
    public final String pendingTasksEndpoint = "pendingTasks";
    public final String killTasksEndpoint = "shutdown";

    public List<TaskStatusJson> getCompleteTasks(String overlord){
        return getTasks(overlord + v1IndexerEndpoint + completeTasksEndpoint);
    }

    public List<TaskStatusJson> getRunningTasks(String overlord){
        return getTasks(overlord + v1IndexerEndpoint + runningTasksEndpoint);
    }

    public List<TaskStatusJson> getWaitingTasks(String overlord){
        return getTasks(overlord + v1IndexerEndpoint + waitingTasksEndpoint);
    }

    public List<TaskStatusJson> getPendingTasks(String overlord){
        return getTasks(overlord + v1IndexerEndpoint + pendingTasksEndpoint);
    }

    private List<TaskStatusJson> getTasks(String overlord) {
        log.info("Calling {}", overlord);
        ResponseEntity<TaskStatusJson[]> responseEntity = restTemplate.getForEntity(overlord, TaskStatusJson[].class);
        TaskStatusJson[] tasks = responseEntity.getBody();
        return Arrays.asList(tasks);
    }

    public void killTask(String taskId, String overlord) {
        String killUrl = overlord + v1IndexerEndpoint + "task/" + taskId + "/" + killTasksEndpoint;
        log.info("Kill task: {} Url: {}", taskId, killUrl);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(killUrl, "" , String.class);
        log.info("Response: {}", responseEntity.getBody());
    }
}
