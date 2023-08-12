
package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.RecentDto;
import com.ecommerce.ecommerce.entity.Item;

import java.util.List;

public interface RecentService {

    void addToRecent(int id);

    List<Item> getRecentItems();
}
