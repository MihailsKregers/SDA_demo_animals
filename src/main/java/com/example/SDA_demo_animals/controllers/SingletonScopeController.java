package com.example.SDA_demo_animals.controllers;

import com.example.SDA_demo_animals.bean_example.CalculatorServiceExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Scope("singleton")
@Controller
public class SingletonScopeController {

    @Autowired
    private CalculatorServiceExample calculatorServiceExample;

    private Integer counter = 0;

    @GetMapping(value = "/singletonScope")
    @ResponseBody
    public String singletonScope() {
        return "My lifecycle is only one request! Requests in lifecycle: " + counter++;
    }
}
