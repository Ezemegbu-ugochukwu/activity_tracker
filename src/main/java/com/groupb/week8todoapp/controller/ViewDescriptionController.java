package com.groupb.week8todoapp.controller;

import com.groupb.week8todoapp.model.Task;
import com.groupb.week8todoapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewDescriptionController {
    public TaskRepository taskRepo;

    @Autowired
    public ViewDescriptionController(TaskRepository taskRepo){
        this.taskRepo = taskRepo;
    }

    @GetMapping("/description{id}")
    public String viewDescription(@PathVariable("id") Integer id, Model model){
        Task task = taskRepo.findById(id).get();

        model.addAttribute("task", task);
        return "viewDescription";
    }
}
