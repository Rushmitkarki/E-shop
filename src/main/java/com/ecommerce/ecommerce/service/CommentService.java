package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.CommentDto;
import com.ecommerce.ecommerce.entity.Comment;

import java.util.List;

public interface CommentService {


    void addComment(CommentDto commentDto);

    List<Comment> getCommentsByItemId(int itemId);
}
