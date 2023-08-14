package com.example.custom_authentication.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class DemoController {
    @GetMapping("/demo")
    public String demo(){
        return "Demo!";
    }

}
