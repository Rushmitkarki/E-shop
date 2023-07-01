package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.ItemDto;
import com.ecommerce.ecommerce.entity.Item;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ItemService {
    void addItem(ItemDto itemDto) throws IOException;

    Optional<Item> getByIdNoOpt(int i) ;

    void deleteItem(int id);

    List<Item> getData();

    List<Item> getByCategory(int id);

    List<Item> getByPartialName(String name);

    List<Item> getFourItems();

    List<Item> getBySellerId(int id);

    long getItemCount();
}
