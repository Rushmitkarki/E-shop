package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.CategoryDto;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.repo.CategoryRepo;
import com.ecommerce.ecommerce.service.CategoryService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoryRepo categoryRepo;

    @BeforeEach
    public void setup(){
//        Optional<Category> category = Optional.of(new Category(null,"test"));
//        Mockito.when(categoryRepo.findById(1)).thenReturn(category);
    }

    @Test
    public void getData() {
        List<Category> categoryList=categoryService.getData();
        List<Category> categoryList1=categoryRepo.findAll();

        assertEquals(categoryList.size(),categoryList1.size());
        for(int i=0;i<categoryList.size();i++){
            assertEquals(categoryList.get(i).getCatId(),categoryList1.get(i).getCatId());
            assertEquals(categoryList.get(i).getCatName(),categoryList1.get(i).getCatName());
        }

    }
}