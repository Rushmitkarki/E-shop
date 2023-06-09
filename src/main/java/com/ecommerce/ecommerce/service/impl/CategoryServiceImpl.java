package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.CategoryDto;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.repo.CategoryRepo;
import com.ecommerce.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl  implements CategoryService {

    private final CategoryRepo categoryRepo;
    @Override
    public void addCategory(CategoryDto categoryDto){
        Category category = new Category();
        category.setCatName(categoryDto.getCatName());
        categoryRepo.save(category);
    }

    @Override
    public List<Category> getData() {
        return categoryRepo.findAll();
    }

    @Override
    public Category getByIdNoOpt(Integer id) {
       return categoryRepo.findById(id).orElse(null);
    }
}
