package com.ecommerce.ecommerce.repo;

import com.ecommerce.ecommerce.entity.Sales;
import com.ecommerce.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SaleRepo extends JpaRepository<Sales,Integer> {
    @Query(value = "select * from sales where sale_date = ?1 and user_id = ?2",nativeQuery = true)
    Optional<Sales> getByDateAndUser(LocalDate date, int userId);

    @Query(value = "select * from sales where sale_date between ?1 and ?2 and user_id = ?3",nativeQuery = true)
    List<Sales> findByDateBetweenAndSeller(LocalDate date1, LocalDate date, int userId);


    @Query(value = "select * from sales where user_id = ?1",nativeQuery = true)
    List<Sales> findBySeller(Integer userId);
}
