package com.ecommerce.ecommerce.controller;


import com.ecommerce.ecommerce.dto.FavoriteDto;
import com.ecommerce.ecommerce.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/favorite")
public class FavoriteController {

    private final FavoriteService favoriteService;
    @PostMapping("/add")
    public String addFavorite(@RequestParam("itemId") int itemId){
        System.out.println("item id is "+itemId);
        favoriteService.add(itemId);
        return "redirect:/buyer/dashboard";
    }
}
