package com.ecommerce.ecommerce.controller;


import com.ecommerce.ecommerce.dto.CartDto;
import com.ecommerce.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/buyer/cart")
@Controller
public class CartController {

    private final CartService cartService;
    @PostMapping("/add")
    public String addToCart(CartDto cartDto){
        System.out.println(cartDto.getItemId());
        cartService.addCart(cartDto);
        return "redirect:/buyer/dashboard";
    }
}
