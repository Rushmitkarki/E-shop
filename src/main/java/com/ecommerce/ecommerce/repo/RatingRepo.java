package com.ecommerce.ecommerce.repo;

import com.ecommerce.ecommerce.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RatingRepo extends JpaRepository<Rating,Integer> {

}
