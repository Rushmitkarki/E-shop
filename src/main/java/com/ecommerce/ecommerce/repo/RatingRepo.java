package com.ecommerce.ecommerce.repo;

import com.ecommerce.ecommerce.entity.Rating;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface RatingRepo extends JpaRepository<Rating,Integer> {
    @Query(value = "select avg(rating) from rating where item_id=?1",nativeQuery = true)
    public Double getAverageRating(int itemId);

    @Query(value = "select * from rating where item_id=?1",nativeQuery = true)
    public List<Rating> getByItemId(Integer itemId);

    @Query(value = "select * from rating where user_id=?1",nativeQuery = true)
    public List<Rating> getByUserId(Integer userId);

    @Query(value = "select * from rating where item_id=?1 and user_id=?2",nativeQuery = true)
    Optional<Rating> getByItemIdAndUserId(int itemId,int userId);

}
