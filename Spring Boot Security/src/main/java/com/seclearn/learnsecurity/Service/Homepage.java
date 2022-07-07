package com.seclearn.learnsecurity.Service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Homepage {
    
    @GetMapping("/")
    public String homepage() {
        return "home.jsp";
    }
    
}
