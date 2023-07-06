package com.ecommerce.ecommerce.repo;


import com.ecommerce.ecommerce.entity.Bill;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepo extends JpaRepository<Bill,Integer> {

    @Query(value = "select * from bill where email= ?1",nativeQuery = true)
    List<Bill> getByUserId(Integer userId);
}
