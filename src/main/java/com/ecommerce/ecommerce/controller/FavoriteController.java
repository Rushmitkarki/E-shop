package com.ecommerce.ecommerce.controller;


import com.ecommerce.ecommerce.dto.FavoriteDto;
import com.ecommerce.ecommerce.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/favorite")
public class FavoriteController {

    private final FavoriteService favoriteService;
    @PostMapping("/add")
    public String addFavorite(FavoriteDto favoriteDto) {
        favoriteService.add(favoriteDto);
        return "redirect:/buyer/dashboard";
    }
}
