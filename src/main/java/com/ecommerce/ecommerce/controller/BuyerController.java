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
import java.util.ArrayList;
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
    @GetMapping("/catalog")
    public String menuByCategory(Model model, @RequestParam(defaultValue = "0") int id,@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "") String partialName) throws IOException {
        int totalItems;
        if(id==0) {
            totalItems = itemService.countAllItems(partialName);
        }
        else{
            totalItems = itemService.countAllItemsByCategoryId(id,partialName);
        }

        int pageSize=6;

        // Calculate the total number of pages based on the page size and total items
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        if(totalPages==0){
            totalPages=1;
        }
        // Ensure the requested page is within the valid range
        if (page < 1) {
            page = 1;
        } else if (page > totalPages) {
            page = totalPages;
        }

        // Calculate the starting index and ending index for the subset of items to display
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalItems);





        List<Item> items =new ArrayList<>();
        if(id==0){
            items=itemService.getSixItems(page,partialName);
        }else{
            items=itemService.getSixItemsByCategoryId(id,page,partialName);
        }


        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("categories", categoryService.getData());
        model.addAttribute("items", items.stream().map(item -> Item.builder()
                .itemId(item.getItemId())
                .itemName(item.getItemName())
                .itemPrice(item.getItemPrice())
                .itemDescription(item.getItemDescription())
                .itemQuantity(item.getItemQuantity())
                .imageBase64(getImageBase64(item.getItemImage()))
                .category(item.getCategory())
                .build()
        ));

        User activeUser = userService.getActiveUser().get();
        model.addAttribute("user",activeUser);
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

    @GetMapping("/item/sort/{order}")
    public String sortCatalog(@PathVariable String order,Model model){
        model.addAttribute("user",userService.getActiveUser().orElse(null));
        model.addAttribute("Categories",categoryService.getData());

        System.out.println(order);
        List<Item> items=itemService.sortItem(order);
        model.addAttribute("items",items.stream().map(item -> Item.builder()
                .itemId(item.getItemId())
                .itemPrice(item.getItemPrice())
                .imageBase64(getImageBase64(item.getItemImage()))
                .itemDescription(item.getItemDescription())
                .build()
        ));

        return "catalog";
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
