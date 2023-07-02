package com.ecommerce.ecommerce.repo;

import com.ecommerce.ecommerce.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepo  extends JpaRepository<Comment, Integer> {

    @Query(value = "select * from comment where item_id=?1",nativeQuery = true)
    List<Comment> getCommentsByItemId(int itemId);
}
