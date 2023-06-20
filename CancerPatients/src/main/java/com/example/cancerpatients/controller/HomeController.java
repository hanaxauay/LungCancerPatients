package com.example.cancerpatients.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "index.html";
    }

    @GetMapping("/organization")
    public String goOrganization(){
        return "organization.html";
    }
    @GetMapping("/greeting")
    public String goGreeting(){
        return "greeting.html";
    }
}
