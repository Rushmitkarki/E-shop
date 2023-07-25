package com.ecommerce.ecommerce.repo;

import com.ecommerce.ecommerce.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {
}
