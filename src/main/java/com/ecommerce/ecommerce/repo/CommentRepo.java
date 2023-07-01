package com.ecommerce.ecommerce.repo;

import com.ecommerce.ecommerce.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo  extends JpaRepository<Comment, Integer> {
}
