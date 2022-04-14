package com.groupb.week8todoapp.controller;

import com.groupb.week8todoapp.model.Task;
import com.groupb.week8todoapp.model.User;
import com.groupb.week8todoapp.services.TaskServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ViewTaskController {
    private final TaskServices taskServices;

    @Autowired
    public ViewTaskController(TaskServices taskServices) {
        this.taskServices = taskServices;
    }

    @GetMapping("/pending")
    public String viewAllPending(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Task> all = taskServices.getAllPendingTasks(user.getId());
        String names = user.getFirstName();
        model.addAttribute("names", names);
        model.addAttribute("pages", all);

        return "dashboard";
    }

    @GetMapping("/progress")
    public String viewAllProgress(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Task> all = taskServices.viewAllInProgressTask(user.getId());
        String names = user.getFirstName();
        model.addAttribute("names", names);
        model.addAttribute("pages", all);

        return "dashboard";
    }

    @GetMapping("/done")
    public String viewAllDone(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Task> all = taskServices.getAllDoneTasks(user.getId());
        String names = user.getFirstName();
        model.addAttribute("names", names);
        model.addAttribute("pages", all);

        return "dashboard";
    }
}
