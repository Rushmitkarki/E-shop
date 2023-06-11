package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.ItemDto;
import com.ecommerce.ecommerce.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    void addItem(ItemDto itemDto , byte[] image);

    Optional<Item> getByIdNoOpt(int i) ;

    List<Item> getData();

    List<Item> getByCategory(int id);
}
