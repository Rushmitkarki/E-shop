package com.ecommerce.ecommerce.repo;

import com.ecommerce.ecommerce.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DiscountRepo extends JpaRepository<Discount,Integer>{
    @Query(value = "select * from discount where discount_status='active'",nativeQuery = true)
    List<Discount> findAllByStatus();

    @Query(value = "select * from discount where discount_code=?1 and discount_status='active'",nativeQuery = true)
    Optional<Discount> findByDiscountCode(String code);
}
