package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.entity.Item;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.service.ItemService;
import com.ecommerce.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/seller")
public class SellerController {
    private final UserService userService;

    private  final ItemService itemService;
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

    @GetMapping("/dashboard")
    public String sellerDashboard(Model model){
        model.addAttribute("user",userService.getActiveUser().orElse(null));
        model.addAttribute("count",itemService.getItemCount());

        return "sellerDashboard";
    }

    @GetMapping("/inventory")
    public String getInventory(Model model){
        User user=userService.getActiveUser().orElse(null);
        model.addAttribute("user",user);
        List<Item> items= itemService.getBySellerId(user.getUserId());
        model.addAttribute("items",items.stream().map(item -> Item.builder()
                .itemId(item.getItemId())
                .itemPrice(item.getItemPrice())
                .imageBase64(getImageBase64(item.getItemImage()))
                .itemDescription(item.getItemDescription())
                .build()

        ));
        return "inventoryManagement";
    }

    public String getImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "/item_img/";
        File file = new File(filePath + fileName);
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return Base64.getEncoder().encodeToString(bytes);
    }
}
