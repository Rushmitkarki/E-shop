package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.UserDto;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ecommerce.ecommerce.dto.ItemDto;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.entity.Item;
import com.ecommerce.ecommerce.repo.ItemRepo;
import com.ecommerce.ecommerce.service.CategoryService;
import com.ecommerce.ecommerce.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    final ItemRepo itemRepo;

    final CategoryService categoryService;

    final private UserService userService;
    @Override
    public void addItem(ItemDto itemDto,byte[] image) {
        Category category = categoryService.getByIdNoOpt(itemDto.getCategory());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.getByEmail(email).orElse(new User());
        Item item= new Item();
        item.setItemName(itemDto.getItemName());
        item.setItemDescription(itemDto.getItemDescription());
        item.setItemPrice(itemDto.getItemPrice());
        item.setItemQuantity(itemDto.getItemQuantity());

        item.setCategory(category);
        item.setUser(user);
        itemRepo.save(item);
    }

    @Override
    public Optional<Item> getByIdNoOpt(int i) {
        return itemRepo.findById(i);
    }

    @Override
    public List<Item> getData() {
        return itemRepo.findAll();
    }

    @Override
    public List<Item> getByCategory(int id) {
        return itemRepo.getByCategory(id);
    }


    @Override
    public List<Item> getByPartialName(String name) {
        return itemRepo.getByPartialName(name);
    }

    public List<Item> getFourItems() {
        List<Item> allItems = getData(); // Assuming this returns a List<Item>
        return allItems.subList(0, Math.min(allItems.size(), 4));
    }
}
