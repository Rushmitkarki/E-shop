package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.NotificationDto;
import com.ecommerce.ecommerce.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationService notificationService;
    @PostMapping("/add")
    public void addNotification(@RequestBody NotificationDto notificationDto){
        notificationService.addNotification(notificationDto);
    }
}
