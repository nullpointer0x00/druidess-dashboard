package com.beatnikstree.druidess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private TaskService taskService;


    @RequestMapping("/index")
    public String index(Model model) {
        List<TaskStatusJson> completeTasks = taskService.getCompleteTasks();
        List<TaskStatusJson> pendingTasks = taskService.getPendingTasks();
        List<TaskStatusJson> runningTasks = taskService.getRunningTasks();
        List<TaskStatusJson> waitingTasks = taskService.getWaitingTasks();
        model.addAttribute("completedTasks", completeTasks);
        model.addAttribute("pendingTasks", pendingTasks);
        model.addAttribute("runningTasks", runningTasks);
        model.addAttribute("waitingTasks", waitingTasks);
        return "index";
    }

}
