package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home"; 
    }

    @GetMapping("/add")
    public String addPage() {
        return "add-record";
    }

    @GetMapping("/records")
    public String recordsPage() {
        return "records";
    }
    
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
}