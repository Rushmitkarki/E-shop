package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.BillDto;
import com.ecommerce.ecommerce.dto.CartDto;
import com.ecommerce.ecommerce.entity.Cart;
import com.ecommerce.ecommerce.entity.Discount;
import com.ecommerce.ecommerce.entity.Notification;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.service.CartService;
import com.ecommerce.ecommerce.service.DiscountService;
import com.ecommerce.ecommerce.service.NotificationService;
import com.ecommerce.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/buyer/cart")
@Controller
public class CartController {

    private final CartService cartService;
    private final UserService userService;
    private final DiscountService discountService;
    private final NotificationService notificationService;

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
        List<Cart> carts = cartService.getDataByUserId(userId);
        int total=0;
        for(Cart cart:carts){
            total+=cart.getTotal();
        }
        model.addAttribute("user",user);
        model.addAttribute("carts", cartService.getDataByUserId(userId));
        model.addAttribute("total",total);

        List<Notification> notification=notificationService.getNotification();
        model.addAttribute("notifications",notification);
        return "viewCart";
    }



    @PostMapping("/delete/{id}")
    public  String deleteCart(@PathVariable int id){
        cartService.deleteCart(id);
        return "redirect:/buyer/cart/view";
    }


}
