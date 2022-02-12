package com.example.SDA_demo_animals.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Controller
public class RequestScopeController {

    private Integer counter = 0;

    @GetMapping(value = "/requestScope")
    @ResponseBody
    public String requestScope() {
        return "My lifecycle is only one request! Requests in lifecycle: " + counter++;
    }
}
