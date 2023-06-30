package com.ecommerce.ecommerce.controller;


import com.ecommerce.ecommerce.dto.RatingDto;
import com.ecommerce.ecommerce.service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequiredArgsConstructor
@Controller
@RequestMapping("/rating")
public class RatingController {
    private final RatingService ratingService;
    @PostMapping("/add")
    public String addReview(@Valid RatingDto ratingDto){

        ratingService.saveRating(ratingDto);

        return "redirect:/buyer/item/"+ratingDto.getItemId();
    }
}
