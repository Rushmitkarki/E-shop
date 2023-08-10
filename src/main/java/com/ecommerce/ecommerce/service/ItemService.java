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




    List<Item> getFourItems();

    List<Item> getBySellerId(int id);

    long getItemCount();

    List<Item> sortItem(String order);

    int countAllItems(String partialName);

    int countAllItemsByCategoryId(int id, String partialName);

    List<Item> getSixItems(int page, String partialName);

    List<Item> getSixItemsByCategoryId(int id, int page, String partialName);

    int countAllItemsBySeller(String partialName,int sellerId);

    int countAllItemsByCategoryIdAndSeller(int id, String partialName,int sellerId);

    List<Item> getSixItemsByCategoryIdAndSeller(int id, int page, String partialName, Integer userId);

    List<Item> getSixItemsBySeller(int page, String partialName, Integer userId);
}
