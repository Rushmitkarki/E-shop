package com.ecommerce.ecommerce.repo;

import com.ecommerce.ecommerce.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Item,Integer>{
//    Get by category
    @Query(value="select * from Item where cat_id = ?1", nativeQuery = true)
    List<Item> getByCategory(int id);
}
