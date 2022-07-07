package com.seclearn.learnsecurity.Service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIService {
    
    @GetMapping("/api")
    public String homepage() {
        return "Hello World!";
    }
    
}
