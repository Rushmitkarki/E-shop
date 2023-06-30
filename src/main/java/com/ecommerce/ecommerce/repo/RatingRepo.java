package com.ecommerce.ecommerce.repo;

import com.ecommerce.ecommerce.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface RatingRepo extends JpaRepository<Rating,Integer> {
    @Query(value = "select avg(rating) from rating where item_id=?1",nativeQuery = true)
    public double getAverageRating(int itemId);
}
