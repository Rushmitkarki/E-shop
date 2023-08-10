package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.service.UserService;
import net.coobird.thumbnailator.Thumbnails;
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

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

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
        int id;
        if(itemDto.getItemId()==null){
            id=0;
        }
        else{
            id=itemDto.getItemId();
        }
        Item item = getByIdNoOpt(id).orElse(new Item());
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

            Path originalFilePath = Paths.get(UPLOAD_DIRECTORY, imageName + fileExtension);
            Files.write(originalFilePath, itemDto.getItemImage().getBytes());

            int desiredWidth = 300;  // Set your desired width
            int desiredHeight = 200; // Set your desired height

            // Provide the path to the output resized image
            String resizedImagePath = UPLOAD_DIRECTORY + "/" + imageName + "_resized" + fileExtension;

            // Resize the image using Thumbnails library
            Thumbnails.of(originalFilePath.toFile())
                    .size(desiredWidth, desiredHeight)
                    .outputFormat(fileExtension.substring(1)) // Remove the dot (.) from the file extension
                    .toFile(resizedImagePath);

            // Set the image name in the database
            item.setItemImage(imageName + "_resized" + fileExtension);
        }

        item.setCategory(category);
        item.setSeller(user);


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


    public List<Item> getFourItems() {
        List<Item> allItems = getData(); // Assuming this returns a List<Item>
        return allItems.subList(0, Math.min(allItems.size(), 4));
    }
    @Override
    public long getItemCount(){
        return itemRepo.count();
    }

    @Override
    public List<Item> sortItem(String order) {
        if(order.equals("A")){
            return itemRepo.orderByNameAsc();
        }
        else if(order.equals("Z")){
            return itemRepo.orderByNameDesc();
        }
        else if(order.equals("L")){
            return itemRepo.orderByPriceAsc();
        }
        else if(order.equals("H")){
            return itemRepo.orderByPriceDesc();
        }
        else{
            return itemRepo.findAll();
        }
    }

    @Override
    public int countAllItems(String partialName) {
        return itemRepo.countAllItems(partialName);
    }

    @Override
    public int countAllItemsByCategoryId(int id, String partialName) {
        return itemRepo.countAllByCategoryId(id, partialName);
    }

    @Override
    public List<Item> getSixItems(int page, String partialName) {
        int offset = (page - 1) * 6;
        return itemRepo.findSixItems(offset, partialName);
    }

    @Override
    public List<Item> getSixItemsByCategoryId(int id, int page, String partialName) {
        return itemRepo.findSixItemsByCategoryId(id, (page - 1) * 6, partialName);
    }

    @Override
    public int countAllItemsBySeller(String partialName, int sellerId) {
        return itemRepo.countAllItemsBySeller(partialName, sellerId);
    }

    @Override
    public int countAllItemsByCategoryIdAndSeller(int id, String partialName, int sellerId) {
        return itemRepo.countAllByCategoryIdAndSeller(id, partialName,sellerId);
    }

    @Override
    public List<Item> getSixItemsByCategoryIdAndSeller(int id, int page, String partialName, Integer sellerId) {
        int offset = (page - 1) * 6;
        return itemRepo.findSixItemsByCategoryIdAndSeller(id, offset, partialName, sellerId);
    }

    @Override
    public List<Item> getSixItemsBySeller(int page, String partialName, Integer sellerId) {
        int offset = (page - 1) * 6;
        return itemRepo.findSixItemsBySeller(offset, partialName, sellerId);
    }

    @Override
    public List<Item> getBySellerId(int id) {
        return itemRepo.getBySellerId(id);
    }
}
