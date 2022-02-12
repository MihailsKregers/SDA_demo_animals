package com.example.SDA_demo_animals.bean_example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanExampleConfig {


    @Bean
    public CalculatorServiceExample calculatorServiceExample() {
        return new CalculatorServiceExample();
    }
}
