package com.ecommerce.ecommerce.repo;

import com.ecommerce.ecommerce.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepo extends JpaRepository<Favorite, Integer> {
}
