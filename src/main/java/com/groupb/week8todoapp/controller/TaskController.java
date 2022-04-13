package com.groupb.week8todoapp.controller;

import com.groupb.week8todoapp.dto.TaskDto;
import com.groupb.week8todoapp.model.User;
import com.groupb.week8todoapp.services.TaskServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class TaskController {

    private final TaskServices taskServices;

    @Autowired
    public TaskController(TaskServices taskServices) {
        this.taskServices = taskServices;
    }


    @GetMapping("/createTask")
    public String createTask(Model model, HttpSession session){
        TaskDto taskDto = new TaskDto();

        model.addAttribute("taskDto", taskDto);
        User user = (User) session.getAttribute("user");
        String names = user.getFirstName();
        model.addAttribute("names",names);
        return "task";
    }

    @PostMapping("/createTask")
    public String createTask(@ModelAttribute("taskDto") TaskDto taskDto){

        taskServices.createTask(taskDto);
        return "task";
    }


}
