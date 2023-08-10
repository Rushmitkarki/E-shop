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

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService{

    private final FavoriteRepo favoriteRepo;
    private final UserService userService;

    private final ItemService itemService;

    @Override
    public void add(FavoriteDto favoriteDto) {
        Favorite favorite = new Favorite();

        User user=userService.getActiveUser().get();
        Item item=itemService.getByIdNoOpt(favoriteDto.getItemId()).get();
        favorite.setUser(user);
        favorite.setItem(item);

        favoriteRepo.save(favorite);
    }
}
