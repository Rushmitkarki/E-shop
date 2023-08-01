package com.ecommerce.ecommerce.repo;


import com.ecommerce.ecommerce.entity.Bill;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;

@Repository
public interface BillRepo extends JpaRepository<Bill,Integer> {

    @Query(value = "select * from bill where email= ?1",nativeQuery = true)
    List<Bill> getByUserId(Integer userId);


    @Query(value = "select * from bill order by bill_id desc limit 1",nativeQuery = true)
    Optional<Bill> findTopByOrderByIdDesc();
}
