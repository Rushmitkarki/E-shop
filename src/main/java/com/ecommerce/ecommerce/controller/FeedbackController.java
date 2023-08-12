package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.FeedbackDto;
import com.ecommerce.ecommerce.entity.Notification;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.service.FeedbackService;
import com.ecommerce.ecommerce.service.NotificationService;
import com.ecommerce.ecommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/feedback")
public class FeedbackController {
    private  final FeedbackService feedbackService;
    private final UserService userService;
    private final NotificationService notificationService;

    @GetMapping("")
    public String feedback(Model model){
        User user   = userService.getActiveUser().orElse(null);
        if (user == null){
            return "redirect:/login";
        }
        model.addAttribute("user",user);
        List<Notification> notification=notificationService.getNotification();
        model.addAttribute("notifications",notification);
        return "Feedback";
    }

    @PostMapping("/save")
    public String saveFeedback(@Valid FeedbackDto feedbackDto){
        feedbackService.saveFeedback(feedbackDto);
        return "redirect:/feedback";
    }
}
