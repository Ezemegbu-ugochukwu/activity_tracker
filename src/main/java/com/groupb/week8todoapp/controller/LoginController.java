package com.groupb.week8todoapp.controller;

import com.groupb.week8todoapp.dto.LoginDto;
import com.groupb.week8todoapp.model.Task;
import com.groupb.week8todoapp.model.User;
import com.groupb.week8todoapp.services.TaskServices;
import com.groupb.week8todoapp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/pages")
public class LoginController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private TaskServices taskServices;

    @GetMapping("/login")
    public String login(Model model){
        LoginDto loginDto = new LoginDto();
        model.addAttribute("loginDto", loginDto);
        return "pages/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginDto") LoginDto loginDto, HttpSession session, Model model){

        User user = userServices.login(loginDto);


        if(user == null){
            String message = "Email or Password Incorrect";

            model.addAttribute("failed", message);
            return "pages/login";
        }


        session.setAttribute("user",user);

        User user1 = (User) session.getAttribute("user");
        String names = user1.getFirstName();

        model.addAttribute("names", names);


        List<Task> all = taskServices.findAllUserTask(user1.getId());
        model.addAttribute("all", all);


        return "/dashboard";

    }

}
