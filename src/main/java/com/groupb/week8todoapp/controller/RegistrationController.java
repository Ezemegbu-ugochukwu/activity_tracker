package com.groupb.week8todoapp.controller;

import com.groupb.week8todoapp.dto.UserDto;
import com.groupb.week8todoapp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserServices userServices;

    @Autowired
    public RegistrationController(UserServices userServices){
        this.userServices = userServices;
    }

    @GetMapping
    public String register(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "register";
    }

    @PostMapping
    public String register(@ModelAttribute("user") UserDto userDto, Model model){

        boolean done = userServices.createUser(userDto);
        if(done){
            return "index";
        }

        model.addAttribute("failed", "Email Already Exists In the system");

        return "redirect:/register";
    }
}
