package com.ecommerce.ecommerce.repo;

import com.ecommerce.ecommerce.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<Item,Integer>{

}
