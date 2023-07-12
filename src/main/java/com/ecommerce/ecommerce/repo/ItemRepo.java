package com.ecommerce.ecommerce.repo;

import com.ecommerce.ecommerce.entity.Item;
import com.ecommerce.ecommerce.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Item,Integer>{
//    Get by category
    @Query(value="select * from Item where cat_id = ?1", nativeQuery = true)
    List<Item> getByCategory(int id);

//    Get by partial name
@Query("SELECT i FROM Item i WHERE LOWER(i.itemName) LIKE LOWER(concat('%', :partialName, '%'))")
List<Item> getByPartialName(@Param("partialName") String partialName);


//Get by seller
    @Query(value="select * from Item where seller_id = ?1", nativeQuery = true)
    List<Item> getBySellerId(int id);


}
