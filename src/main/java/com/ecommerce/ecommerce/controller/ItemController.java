package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.ItemDto;
import com.ecommerce.ecommerce.dto.UserDto;
import com.ecommerce.ecommerce.entity.Item;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.service.CategoryService;
import com.ecommerce.ecommerce.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

@Controller
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {
    final CategoryService categoryService;
    final ItemService itemService;
    @GetMapping("/add")
    public String getAddItemPage(Model model){
        model.addAttribute("categories", categoryService.getData());

        return "addItem.html";
    }



    @PostMapping("/save")
    public String saveItem(ItemDto itemDto, @RequestParam("itemImage") MultipartFile itemImage) throws IOException {


        byte[] imageBytes = itemImage.getBytes();
        // Call your itemService to save the itemDto object
        itemService.addItem(itemDto,imageBytes);

        return "redirect:/item/add";
    }

    @GetMapping("/catalog/{Id}")
    public String getCatalog(Model model, @PathVariable int Id){

        model.addAttribute("Categories",categoryService.getData());
        model.addAttribute("Category",categoryService.getByIdNoOpt(Id));
        model.addAttribute("items",itemService.getByCategory(Id));
        return "catalog";
    }

}
