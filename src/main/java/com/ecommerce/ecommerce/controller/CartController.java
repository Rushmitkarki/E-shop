package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.BillDto;
import com.ecommerce.ecommerce.dto.CartDto;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.service.CartService;
import com.ecommerce.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/buyer/cart")
@Controller
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    @PostMapping("/add")
    public String addToCart(CartDto cartDto) {
        System.out.println(cartDto.getItemId());
        cartService.addCart(cartDto);
        return "redirect:/buyer/dashboard";
    }

    @GetMapping("/view")
    public String viewCart(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.getByEmail(email).orElse(new User());
        int userId = user.getUserId();
        model.addAttribute("carts", cartService.getDataByUserId(userId));
        return "viewCart";
    }

    @PostMapping("/delete/{id}")
    public  String deleteCart(@PathVariable int id){
        cartService.deleteCart(id);
        return "redirect:/buyer/cart/view";
    }
}
