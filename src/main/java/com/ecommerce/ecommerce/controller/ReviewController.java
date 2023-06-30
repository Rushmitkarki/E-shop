package com.ecommerce.ecommerce.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/review")
public class ReviewController {

    @PostMapping("/add")
    public String addReview(){
        return "redirect:/buyer/item/{id}";
    }
}
