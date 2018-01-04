package com.beatnikstree.druidess;

import com.beatnikstree.druidess.data.EnvironmentSettings;
import com.beatnikstree.druidess.data.EnvironmentSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class IndexController {

    @Autowired
    private EnvironmentSettingsRepository environmentSettingsRepository;

    @Autowired
    private TaskService taskService;


    @RequestMapping("/index")
    public String index(HttpSession session, @RequestParam(value = "env") Optional<Integer> env, Model model) {

        if (session.getAttribute("envs") == null) {
            session.setAttribute("envs", environmentSettingsRepository.findAll());
        }

        EnvironmentSettings des;
        if (env.isPresent()) {
            des = environmentSettingsRepository.findById(env.get());
        } else {
            des = environmentSettingsRepository.findFirstByIsDefaultTrue();
        }
        session.setAttribute("env", des);
        return "index";
    }

    @RequestMapping("/tasks/{taskType}")
    public String taskTable(HttpSession session, @PathVariable("taskType") String taskType, Model model) {
        EnvironmentSettings des = (EnvironmentSettings) session.getAttribute("env");
        List<TaskStatusJson> tasks = null;
        String tableId = null;
        String type = null;
        String fragmentPart = "task-table";
        if ("completeTasks".equals(taskType)) {
            tasks = taskService.getCompleteTasks(des.getOverlordUrl());
            tableId = "complete_task_table";
            type = "Completed";
            fragmentPart = "completed-" + fragmentPart;
        } else if ("pendingTasks".equals(taskType)) {
            tasks = taskService.getPendingTasks(des.getOverlordUrl());
            tableId = "pending_task_table";
            type = "Pending";
        } else if ("runningTasks".equals(taskType)) {
            tasks = taskService.getRunningTasks(des.getOverlordUrl());
            tableId = "running_task_table";
            type = "Running";
        } else if ("waitingTasks".equals(taskType)) {
            tasks = taskService.getWaitingTasks(des.getOverlordUrl());
            tableId = "waiting_task_table";
            type = "Waiting";
        }
        model.addAttribute("type", type);
        model.addAttribute("tableId", tableId);
        List<TaskStatusModel> taskModels = new ArrayList<>();
        tasks.stream().forEach(t -> taskModels.add(new TaskStatusModel(t.getId(), t, false)));
        model.addAttribute("tasks", taskModels);
        TaskFormData taskFormData = new TaskFormData(taskModels);
        model.addAttribute("taskFormData", taskFormData);
        return "fragments/task-table :: " + fragmentPart;
    }

    @PostMapping("/kill")
    public String kill(HttpServletRequest request, HttpSession httpSession, @ModelAttribute(name = "taskFormData") TaskFormData taskFormData) {
        EnvironmentSettings des = (EnvironmentSettings) httpSession.getAttribute("env");
        for (TaskStatusModel task : taskFormData.getTaskStatusModels()) {
            if ("selected".equals(request.getParameter("kill")) && task.getIsSelected()) {
                taskService.killTask(task.getId(), des.getOverlordUrl());
            } else if ("all".equals(request.getParameter("kill"))) {
                taskService.killTask(task.getId(), des.getOverlordUrl());
            }
        }
        return "redirect:index?env=" + des.getId();
    }

}
