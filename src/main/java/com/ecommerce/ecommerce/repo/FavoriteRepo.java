package com.ecommerce.ecommerce.repo;

import com.ecommerce.ecommerce.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepo extends JpaRepository<Favorite, Integer> {

    @Query(value = "select * from favorite where item_id=?1 and user_id=?2", nativeQuery = true)
    Optional<Favorite> findByItemId(int itemId, int userId);

    @Query(value = "select * from favorite where user_id=?1", nativeQuery = true)
    List<Favorite> findByUserId(Integer userId);

}
