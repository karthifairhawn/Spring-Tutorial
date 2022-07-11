package com.seclearn.learnsecurity.Service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIService {
    
    @GetMapping("/")
    public String homepage() {
        return "Guest user!";
    }

    @GetMapping("/user")
    public String homepage1() {
        return "User and admin can access";
    }

    @GetMapping("/admin")
    public String homepage2() {
        return "Only admin area";
    }
    
}
