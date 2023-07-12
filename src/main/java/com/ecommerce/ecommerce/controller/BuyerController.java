package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.entity.Item;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping("/buyer")
public class BuyerController {

    private final CategoryService categoryService;
    private final ItemService itemService;
    private final UserService userService;
    private final RatingService ratingService;
    private final CommentService commentService;
    @GetMapping("/catalog/{Id}")
    public String getCatalog(Model model, @PathVariable int Id){
        model.addAttribute("user",userService.getActiveUser().orElse(null));
        model.addAttribute("Categories",categoryService.getData());
        model.addAttribute("Category",categoryService.getByIdNoOpt(Id));
        List<Item> items=itemService.getByCategory(Id);
        model.addAttribute("items",items.stream().map(item -> Item.builder()
                .itemId(item.getItemId())
                .itemPrice(item.getItemPrice())
                .imageBase64(getImageBase64(item.getItemImage()))
                .itemDescription(item.getItemDescription())
                .build()
        ));

        return "catalog";
    }

    @GetMapping("/catalog")
    public String getCatalog(Model model){

        model.addAttribute("user",userService.getActiveUser().orElse(null));
        model.addAttribute("Categories",categoryService.getData());
        List<Item> items=itemService.getData();
        model.addAttribute("items",items.stream().map(item -> Item.builder()
                .itemId(item.getItemId())
                .itemPrice(item.getItemPrice())
                .imageBase64(getImageBase64(item.getItemImage()))
                .itemDescription(item.getItemDescription())
                .build()
        ));

        return "catalog";
    }

    @PostMapping("/search")
    public String getSearch(Model model,@RequestParam("searchName") String name){

        model.addAttribute("user",userService.getActiveUser().orElse(null));
        model.addAttribute("Categories",categoryService.getData());
        List<Item> items=itemService.getByPartialName(name);
        model.addAttribute("items",items.stream().map(item -> Item.builder()
                        .itemId(item.getItemId())
                        .itemPrice(item.getItemPrice())
                        .imageBase64(getImageBase64(item.getItemImage()))
                        .itemDescription(item.getItemDescription())
                        .build()

                ));

        return "catalog";
    }

    @GetMapping("dashboard")
    public String getDashboard(Model model){

        model.addAttribute("count",itemService.getItemCount());
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
        model.addAttribute("imageBase64" ,getImageBase64(item.getItemImage()));
        model.addAttribute("rating",ratingService.getAverageRating(Id).orElse(.0));
        model.addAttribute("comments",commentService.getCommentsByItemId(Id));
        User user=userService.getActiveUser().orElse(null);
        return "viewItems";
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
