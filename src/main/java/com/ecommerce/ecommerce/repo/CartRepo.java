package com.ecommerce.ecommerce.repo;

import com.ecommerce.ecommerce.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepo extends JpaRepository<Cart,Integer> {
    @Query(value = "select * from cart where user_id = ?1",nativeQuery = true)
    List<Cart> findByUserId(int userId);


}
