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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Base64;

@Controller
@RequiredArgsConstructor
@RequestMapping("/seller/item")
public class ItemController {
    final CategoryService categoryService;
    final ItemService itemService;
    @GetMapping("/add")
    public String getAddItemPage(Model model){
        model.addAttribute("categories", categoryService.getData());

        return "addItem.html";
    }



    @PostMapping("/save")
    public String saveItem(ItemDto itemDto) throws IOException {



        // Call your itemService to save the itemDto object
        itemService.addItem(itemDto);

        return "redirect:/seller/item/add";
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
        String base64 = Base64.getEncoder().encodeToString(bytes);
        return base64;
    }


}
