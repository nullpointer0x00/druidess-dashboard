package com.beatnikstree.druidess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class IndexController {

    private Map<Integer, DruidEnvironmentSettings> druidEnvironmentSettings;
    private List<DruidEnvironmentSettings> envs;
    private Integer default_env = 3;

    @Autowired
    private TaskService taskService;

    @PostConstruct
    public void init() {
        druidEnvironmentSettings = new HashMap<>();
        envs = new ArrayList<>();
        DruidEnvironmentSettings des1 = new DruidEnvironmentSettings(1, "Dev1", "http://dev1:8080", "#a6c7fc");
        DruidEnvironmentSettings des2 = new DruidEnvironmentSettings(2, "Dev2", "http://dev2.cc:8080", "#f7c980");
        DruidEnvironmentSettings des3 = new DruidEnvironmentSettings(3, "Dev3", "http://dev3:8080", "#ff7aba");
        DruidEnvironmentSettings des4 = new DruidEnvironmentSettings(4, "Dev4", "http://dev4:8080", "#8ee594");
        DruidEnvironmentSettings des5 = new DruidEnvironmentSettings(5, "PROD", "http://prod:8080", "#ff0a33");
        DruidEnvironmentSettings des6 = new DruidEnvironmentSettings(6, "INT", "http://int:8080", "#f30a33");
        druidEnvironmentSettings.put(des1.id, des1);
        druidEnvironmentSettings.put(des2.id, des2);
        druidEnvironmentSettings.put(des3.id, des3);
        druidEnvironmentSettings.put(des4.id, des4);
        druidEnvironmentSettings.put(des5.id, des5);
        druidEnvironmentSettings.put(des6.id, des6);
        envs.add(des1);
        envs.add(des2);
        envs.add(des3);
        envs.add(des4);
        envs.add(des5);
        envs.add(des6);

    }

    @RequestMapping("/index")
    public String index(HttpSession session, @RequestParam(value = "env") Optional<Integer> env, Model model) {

        if (session.getAttribute("envs") == null) {
            session.setAttribute("envs", envs);
        }

        DruidEnvironmentSettings des;
        if (env.isPresent() && druidEnvironmentSettings.get(env.get()) != null) {
            des = druidEnvironmentSettings.get(env.get());
            session.setAttribute("env", des);
        } else {
            des = envs.get(default_env);
            session.setAttribute("env", des);
        }
        return "index";
    }

    @RequestMapping("/tasks/{taskType}")
    public String taskTable(HttpSession session, @PathVariable("taskType") String taskType, Model model) {
        DruidEnvironmentSettings des = (DruidEnvironmentSettings) session.getAttribute("env");
        List<TaskStatusJson> tasks = null;
        String tableId = null;
        String type = null;
        String fragmentPart = "task-table";
        if ("completeTasks".equals(taskType)) {
            tasks = taskService.getCompleteTasks(des.overlordUrl);
            tableId = "complete_task_table";
            type = "Completed";
            fragmentPart = "completed-" + fragmentPart;
        } else if ("pendingTasks".equals(taskType)) {
            tasks = taskService.getPendingTasks(des.overlordUrl);
            tableId = "pending_task_table";
            type = "Pending";
        } else if ("runningTasks".equals(taskType)) {
            tasks = taskService.getRunningTasks(des.overlordUrl);
            tableId = "running_task_table";
            type = "Running";
        } else if ("waitingTasks".equals(taskType)) {
            tasks = taskService.getWaitingTasks(des.overlordUrl);
            tableId = "waiting_task_table";
            type = "Waiting";
        }
        model.addAttribute("type", type);
        model.addAttribute("tableId", tableId);
        List<TaskStatusModel> taskModels = new ArrayList<>();
        tasks.stream().forEach(t -> taskModels.add(new TaskStatusModel(t, false)));
        model.addAttribute("tasks", taskModels);
        return "fragments/task-table :: " + fragmentPart;
    }

    @RequestMapping("/kill-all")
    public String killAll(HttpSession httpSession, @ModelAttribute ArrayList<TaskStatusModel> tasks) {
        DruidEnvironmentSettings des = (DruidEnvironmentSettings) httpSession.getAttribute("env");
        for (TaskStatusModel task : tasks) {
            taskService.killTask(task.getUrlEncodedId(), des.overlordUrl);
        }
        return "redirect:index?env=" + des.id;
    }

    @RequestMapping("/kill-selected")
    public String killSelected(HttpSession httpSession, @ModelAttribute ArrayList<TaskStatusModel> tasks) {
        DruidEnvironmentSettings des = (DruidEnvironmentSettings) httpSession.getAttribute("env");
        for (TaskStatusModel task : tasks) {
            if(task.getIsSelected()) {
                taskService.killTask(task.getUrlEncodedId(), des.overlordUrl);
            }
        }
        return "redirect:index?env=" + des.id;
    }

}
