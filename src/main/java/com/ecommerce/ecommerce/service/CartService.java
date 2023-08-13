package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.CartDto;
import com.ecommerce.ecommerce.entity.Bill;
import com.ecommerce.ecommerce.entity.Cart;

import java.util.List;

public interface CartService {

    void addCart(CartDto cartDto);

    List<Cart> getDataByUserId(int UserId);

    void deleteCart(int id);

    void setStatusTime(Cart cart);

    List<Cart> getCartByBillId(int billId);

    void addBillId(Bill bill, Cart cart);

    List<Cart> getByItemId(Integer itemId);

    List<Cart> getByItemIdAndStatus(Integer itemId);
}
