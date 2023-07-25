package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.FeedbackDto;
import com.ecommerce.ecommerce.service.FeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/feedback")
public class FeedbackController {
    private  final FeedbackService feedbackService;
    @GetMapping("")
    public String feedback(){
        return "Feedback";
    }

    @PostMapping("/save")
    public String saveFeedback(@Valid FeedbackDto feedbackDto){
        feedbackService.saveFeedback(feedbackDto);
        return "redirect:/feedback";
    }
}
