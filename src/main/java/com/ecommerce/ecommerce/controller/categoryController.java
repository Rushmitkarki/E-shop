package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.CategoryDto;
import com.ecommerce.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/category")
public class categoryController {
     final CategoryService categoryService;
    @PostMapping("/add")
    public String addCategory(@Valid CategoryDto categoryDto){
        categoryService.addCategory(categoryDto);
       return "redirect:/item/add";
    }
}
