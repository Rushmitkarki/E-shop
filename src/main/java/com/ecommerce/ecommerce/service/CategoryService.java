package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.CategoryDto;
import com.ecommerce.ecommerce.entity.Category;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void addCategory(CategoryDto categoryDto);

    List<Category> getData();

    Optional<Category> getByIdNoOpt(Integer id);


}
