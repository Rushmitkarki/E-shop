package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.FavoriteDto;
import com.ecommerce.ecommerce.entity.Favorite;
import com.ecommerce.ecommerce.entity.Item;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.repo.FavoriteRepo;
import com.ecommerce.ecommerce.service.FavoriteService;
import com.ecommerce.ecommerce.service.ItemService;
import com.ecommerce.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService{

    private final FavoriteRepo favoriteRepo;
    private final UserService userService;

    private final ItemService itemService;

    private final  String filePath = System.getProperty("user.dir") + "/item_img/";

    @Override

    public void add(int itemId) {

        User user=userService.getActiveUser().get();
        Item item=itemService.getByIdNoOpt(itemId).get();
        Favorite favorite = favoriteRepo.findByItemId(itemId,user.getUserId()).orElse(new Favorite());
        favorite.setUser(user);
        favorite.setItem(item);
        favoriteRepo.save(favorite);
    }

    @Override
    public boolean isFavorite(int itemId){
        User user=userService.getActiveUser().get();
        return favoriteRepo.findByItemId(itemId,user.getUserId()).isPresent();
    }

    @Override
    public List<Favorite> getFavoriteByUser(){
        User user=userService.getActiveUser().get();
        return favoriteRepo.findByUserId(user.getUserId());
    }

    @Override
    public List<Item> getFavoriteItemsByUser() {
        User user=userService.getActiveUser().get();
        List<Favorite> favorites=favoriteRepo.findByUserId(user.getUserId());
        List<Item> items=favorites.stream().map(Favorite::getItem).toList();
        return items.stream().map(item -> Item.builder()
                .itemId(item.getItemId())
                .itemName(item.getItemName())
                .itemPrice(item.getItemPrice())
                .itemDescription(item.getItemDescription())
                .imageBase64(getImageBase64(item.getItemImage()))
                .build()).toList();
    }
    private String getImageBase64(String fileName) {
        File file = new File(filePath + fileName);
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return Base64.getEncoder().encodeToString(bytes);
    }
}
