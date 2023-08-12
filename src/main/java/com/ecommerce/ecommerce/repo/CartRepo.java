package com.ecommerce.ecommerce.repo;

import com.ecommerce.ecommerce.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepo extends JpaRepository<Cart,Integer> {
    @Query(value = "select * from cart where user_id = ?1 and cart_status = 'unPaid' ",nativeQuery = true)
    List<Cart> findByUserId(int userId);

    @Query(value = "select * from cart where item_id = ?1",nativeQuery = true)
    List<Cart> getByItemId(Integer itemId);

    @Query(value = "select * from cart where bill_id = ?1",nativeQuery = true)
    List<Cart> findByBillId(Integer billId);


    @Query(value = "select * from cart where item_id = ?1 and cart_status = 'Paid' ",nativeQuery = true)
    List<Cart> getByItemIdAndStatus(Integer itemId);
}
