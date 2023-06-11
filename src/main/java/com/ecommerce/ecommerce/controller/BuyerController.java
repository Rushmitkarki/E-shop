package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.entity.Item;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.service.CategoryService;
import com.ecommerce.ecommerce.service.ItemService;
import com.ecommerce.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/buyer")
public class BuyerController {

    private final CategoryService categoryService;
    private final ItemService itemService;
    private final UserService userService;
    @GetMapping("/catalog/{Id}")
    public String getCatalog(Model model, @PathVariable int Id){
        model.addAttribute("user",userService.getActiveUser().orElse(null));
        model.addAttribute("Categories",categoryService.getData());
        model.addAttribute("Category",categoryService.getByIdNoOpt(Id));
        model.addAttribute("items",itemService.getByCategory(Id));
        return "catalog";
    }

    @GetMapping("/catalog")
    public String getCatalog(Model model){

        model.addAttribute("user",userService.getActiveUser().orElse(null));
        model.addAttribute("Categories",categoryService.getData());
        model.addAttribute("items",itemService.getData());
        return "catalog";
    }

    @PostMapping("/search")
    public String getSearch(Model model,@RequestParam("searchName") String name){

        model.addAttribute("user",userService.getActiveUser().orElse(null));
        model.addAttribute("Categories",categoryService.getData());
        model.addAttribute("items",itemService.getByPartialName(name));
        return "catalog";
    }

    @GetMapping("dashboard")
    public String getDashboard(Model model){

        model.addAttribute("user",userService.getActiveUser().orElse(null));
        model.addAttribute("Categories",categoryService.getData());
        model.addAttribute("items",itemService.getFourItems());
        return "Dashboard";
    }

    @GetMapping("/item/{Id}")
    public String getItem(Model model, @PathVariable int Id){
        model.addAttribute("user",userService.getActiveUser().orElse(null));
        model.addAttribute("Categories",categoryService.getData());
        Item item=itemService.getByIdNoOpt(Id).orElse(null);
        model.addAttribute("item",item);
        return "viewItems";
    }
}
