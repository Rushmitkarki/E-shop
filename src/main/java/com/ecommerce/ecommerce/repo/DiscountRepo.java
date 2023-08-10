package com.ecommerce.ecommerce.repo;

import com.ecommerce.ecommerce.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiscountRepo extends JpaRepository<Discount,Integer>{
    @Query(value = "select * from discount where discount_status='active'",nativeQuery = true)
    List<Discount> findAllByStatus();
}
