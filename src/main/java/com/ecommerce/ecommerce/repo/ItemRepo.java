package com.ecommerce.ecommerce.repo;

import com.ecommerce.ecommerce.entity.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Item,Integer>{

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


    @Query(value = "select * from Item where LOWER(item_name) LIKE CONCAT('%', LOWER(?2), '%') and seller_id = ?3 order by item_id offset ?1 limit 6", nativeQuery = true)
    List<Item> findSixItemsBySeller(int offset, String partialName, Integer sellerId);

    @Query(value = "select * from Item where cat_id = ?1 and LOWER(item_name) LIKE CONCAT('%', LOWER(?3), '%') and seller_id = ?4 order by item_id offset ?2 limit 6", nativeQuery = true)
    List<Item> findSixItemsByCategoryIdAndSeller(int id, int offset, String partialName, Integer sellerId);

    @Query(value = "select count(*) from Item where cat_id = ?1 and LOWER(item_name) LIKE CONCAT('%', LOWER(?2), '%') and seller_id = ?3", nativeQuery = true)
    int countAllByCategoryIdAndSeller(int id, String partialName, int sellerId);

    @Query(value = "select count(*) from Item where LOWER(item_name) LIKE CONCAT('%', LOWER(?1), '%') and seller_id = ?2", nativeQuery = true)
    int countAllItemsBySeller(String partialName, int sellerId);
}

