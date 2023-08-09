package com.ecommerce.ecommerce.repo;

import com.ecommerce.ecommerce.entity.Item;

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

    @Query(value="select * from Item where price = ?1 order by name", nativeQuery = true)
    List<Item> getItemByPrice(Integer price);


    @Query(value="select * from Item order by item_name", nativeQuery = true)
    List<Item> orderByNameAsc();

    @Query(value="select * from Item order by item_name desc", nativeQuery = true)
    List<Item> orderByNameDesc();

    @Query(value="select * from Item order by item_price", nativeQuery = true)
    List<Item> orderByPriceAsc();

    @Query(value="select * from Item order by item_price desc", nativeQuery = true)
    List<Item> orderByPriceDesc();

    @Query(value = "select * from Item where cat_id = ?1 and LOWER(item_name) LIKE CONCAT('%', LOWER(?3), '%') order by item_id offset ?2 limit 6", nativeQuery = true)
    List<Item> findSixItemsByCategoryId(int id, int offset, String partialName);

    @Query(value = "select * from Item where LOWER(item_name) LIKE CONCAT('%', LOWER(?2), '%') order by item_id  offset ?1 limit 6", nativeQuery = true)
    List<Item> findSixItems(int offset, String partialName);

    @Query(value = "select count(*) from Item where LOWER(item_name) LIKE CONCAT('%', LOWER(?1), '%')", nativeQuery = true)
    int countAllItems(String partialName);

    @Query(value = "select count(*) from Item where cat_id = ?1 and LOWER(item_name) LIKE CONCAT('%', LOWER(?2), '%')", nativeQuery = true)
    int countAllByCategoryId(int id, String partialName);
}

