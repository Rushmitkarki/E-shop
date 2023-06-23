package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.CartDto;
import com.ecommerce.ecommerce.entity.Cart;

import java.util.List;

public interface CartService {

    void addCart(CartDto cartDto);

    List<Cart> getDataByUserId(int UserId);

    void deleteCart(int id);

    void setStatus(int id);
}
