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
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    final ItemRepo itemRepo;

    final CategoryService categoryService;

    final private UserService userService;

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/item_img";
    @Override
    public void addItem(ItemDto itemDto) throws IOException {



        Category category = categoryService.getByIdNoOpt(itemDto.getCategory());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.getByEmail(email).orElse(new User());
        Item item = getByIdNoOpt(itemDto.getItemId()).orElse(new Item());
        item.setItemName(itemDto.getItemName());
        item.setItemDescription(itemDto.getItemDescription());
        item.setItemPrice(itemDto.getItemPrice());
        item.setItemQuantity(itemDto.getItemQuantity());

        if (itemDto.getItemImage() != null) {
            // Generate a unique image file name using user email and item name
            String imageName = user.getFname()+"_"+ itemDto.getItemName() + "_" + System.currentTimeMillis();
            System.out.println(imageName);
            String originalFilename = itemDto.getItemImage().getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));

            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, imageName + fileExtension);
            fileNames.append(imageName + fileExtension);
            Files.write(fileNameAndPath, itemDto.getItemImage().getBytes());

            item.setItemImage(imageName + fileExtension);
        }

        item.setCategory(category);
        item.setUser(user);


        itemRepo.save(item);
    }

    @Override
    public Optional<Item> getByIdNoOpt(int i) {
        return itemRepo.findById(i);
    }

    @Override
    public void deleteItem(int id) {
        itemRepo.deleteById(id);
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
    @Override
    public long getItemCount(){
        return itemRepo.count();
    }

    @Override
    public List<Item> getBySellerId(int id) {
        return itemRepo.getBySellerId(id);
    }
}
