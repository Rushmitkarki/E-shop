package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.FavoriteDto;
import com.ecommerce.ecommerce.entity.Favorite;
import com.ecommerce.ecommerce.entity.Item;

import java.util.List;

public interface FavoriteService {


    void add(int itemId);

    boolean isFavorite(int itemId);

    List<Favorite> getFavoriteByUser();

    List<Item> getFavoriteItemsByUser();
}
