package com.groupb.week8todoapp.controller;

import com.groupb.week8todoapp.dto.EditTaskDto;
import com.groupb.week8todoapp.dto.TaskDto;
import com.groupb.week8todoapp.model.Task;
import com.groupb.week8todoapp.model.User;
import com.groupb.week8todoapp.services.TaskServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

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

        return "addToDo";
    }

    @PostMapping("/createTask")
    public String createTask(@ModelAttribute("taskDto") TaskDto taskDto, HttpSession session, RedirectAttributes redirectAttributes){
        User user = (User) session.getAttribute("user");
        int id = user.getId();
        taskServices.createTask(taskDto, id);

        return "addToDo";
    }


    @GetMapping("/viewAllTask")
    public String getAllTask(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Task> all = taskServices.findAllUserTask(user.getId());
        String names = user.getFirstName();
        model.addAttribute("names", names);
        model.addAttribute("pages", all);
        return "dashboard";
    }

    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable("id") Integer id, Model model, HttpSession session){

        EditTaskDto editTaskDto = new EditTaskDto();
        editTaskDto.setId(id);

        editTaskDto =  taskServices.populate(editTaskDto, id);
        model.addAttribute("editTaskDto", editTaskDto);
        session.setAttribute("id",id);

        return "editTask";
    }
    @PostMapping("/edit")
    public String editTask(@ModelAttribute("editTaskDto") EditTaskDto editTaskDto, HttpSession session, Model model){
        int id = (int) session.getAttribute("id");
        editTaskDto.setId(id);
        taskServices.editTask(editTaskDto);



        return "dashboard";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        taskServices.deleteTask(user.getId(),id);
        return getAllTask(model, session);
    }

}
