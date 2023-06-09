package com.ecommerce.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class LoginController {
    @GetMapping("/login")
    public String getLoginPage(){
        return "login.html";
    }

    @GetMapping("/dashboard")
    public String openDashboard(){
        return "dashboard";
    }
}
