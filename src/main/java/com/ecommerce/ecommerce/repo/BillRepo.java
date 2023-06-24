package com.ecommerce.ecommerce.repo;


import com.ecommerce.ecommerce.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepo extends JpaRepository<Bill,Integer> {
}
