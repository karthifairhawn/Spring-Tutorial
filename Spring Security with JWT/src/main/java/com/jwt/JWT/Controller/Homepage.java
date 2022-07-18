package com.jwt.JWT.Controller;

import javax.annotation.Generated;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Homepage {
    
    @RequestMapping({"/"})
    public String index() {
        return "index";
    }

    @GetMapping("/admin/home")
    public String adminHome() {
        return "adminHome";
    }

    
}
