package com.example.SDA_demo_animals.security.controller;

import com.example.SDA_demo_animals.security.models.User;
import com.example.SDA_demo_animals.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public void setUserDetailsService(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/registerUser")
    @ResponseBody
    public void registerUser(@RequestBody User user) {
        userDetailsService.registerUser(user);
    }

}
