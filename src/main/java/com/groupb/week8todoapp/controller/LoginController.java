package com.groupb.week8todoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/pages")
public class LoginController {

//    @GetMapping("/signin")
//    public String signin(){
//        return "pages/signIn";
//    }

    @GetMapping("/login")
    public String login(){
        return "pages/login";
    }
}
