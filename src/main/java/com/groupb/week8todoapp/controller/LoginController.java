package com.groupb.week8todoapp.controller;

import com.groupb.week8todoapp.dto.LoginDto;
import com.groupb.week8todoapp.model.User;
import com.groupb.week8todoapp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/pages")
public class LoginController {

    @Autowired
    private UserServices userServices;

//    @GetMapping("/signin")
//    public String signin(){
//        return "pages/signIn";
//    }

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

            model.addAttribute("message", message);
            return "pages/login";
        }


        session.setAttribute("user",user);

        User user1 = (User) session.getAttribute("user");
        String names = "Hi,"+"  "+user1.getFirstName()+" "+user1.getLastName();

        model.addAttribute("names", names);


        return "/dashboard";

    }

}
