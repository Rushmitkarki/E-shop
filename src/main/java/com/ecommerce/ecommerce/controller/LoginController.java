package com.ecommerce.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class LoginController {
    @GetMapping("/login")
    public String getLoginPage(){
        return "login.html";
    }

    @PostMapping("/logout")
    public String logout(Authentication authentication){
        if (authentication.isAuthenticated()){
            SecurityContextHolder.clearContext();
        }
        return "redirect:/login";
    }
}
