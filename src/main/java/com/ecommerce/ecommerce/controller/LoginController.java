package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.service.NotificationService;
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
    private final NotificationService notificationService;
    @GetMapping("/login")
    public String getLoginPage(){
        notificationService.addNotification();
        return "login";
    }

    @PostMapping("/logout")
    public String logout(Authentication authentication){
        SecurityContextHolder.clearContext();
        return "redirect:/user/login";
    }
}
