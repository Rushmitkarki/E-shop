package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.BillDto;
import com.ecommerce.ecommerce.entity.Bill;
import com.ecommerce.ecommerce.entity.Cart;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.service.BillService;
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

import java.util.List;
import java.util.Objects;

@RequestMapping("/buyer/bill")
@RequiredArgsConstructor
@Controller
public class BillController {
    private final BillService billService;

    private final CartService cartService;

    private final UserService userService;

    @GetMapping("/checkout")
    public String getBill(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.getByEmail(email).orElse(new User());
        int userId = user.getUserId();
        model.addAttribute("carts", cartService.getDataByUserId(userId));
        return "checkout";
    }

    @PostMapping("/save")
    public String saveBill(BillDto billDto) {

        System.out.println(billDto.getBillId());
        System.out.println(billDto.getBillSubAmount());
        User user = userService.getActiveUser().orElse(new User());
        int userId = user.getUserId();
        List<Cart> carts = cartService.getDataByUserId(userId);
        for (Cart cart : carts) {
            cartService.setStatus(cart.getId());
        }
        billService.saveBill(billDto);
        if (Objects.equals(billDto.getBillPayment(), "Cash")) {
            return "redirect:/buyer/bill/checkout";
        }

        return "redirect:/buyer/bill/QR";

    }

    @GetMapping("/QR")
    public String getQR() {
        return "multiplePaymentMethod";
    }

    @PostMapping("/upload")
    public String uploadBill(BillDto billDto) {
        billService.saveBill(billDto);
        return "redirect:/buyer/bill/checkout";
    }
}
