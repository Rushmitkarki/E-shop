package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/seller")
public class sellerController {
    private final UserService userService;
    @GetMapping("/verify/{email}")
    public String sellerVerify(@PathVariable String email, Model model){
        model.addAttribute("email", email);

        return "sellerVerify";
    }
    @PostMapping("/verify/{email}")
    public String sellerVerify(@PathVariable String email, @RequestParam("panNumber") String panNumber){
        userService.verifySeller(email,panNumber);
        return "redirect:/login?success";
    }
}
