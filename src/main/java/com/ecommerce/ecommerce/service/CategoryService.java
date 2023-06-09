package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.CategoryDto;
import com.ecommerce.ecommerce.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    void addCategory(CategoryDto categoryDto);

    List<Category> getData();

    Category getByIdNoOpt(Integer id);
}
