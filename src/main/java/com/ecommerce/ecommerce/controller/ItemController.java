package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.ItemDto;
import com.ecommerce.ecommerce.dto.UserDto;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.service.CategoryService;
import com.ecommerce.ecommerce.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

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
    public String saveItem(ItemDto itemDto) {



        // Call your itemService to save the itemDto object
        itemService.addItem(itemDto);

        return "redirect:/item/add";
    }

}
