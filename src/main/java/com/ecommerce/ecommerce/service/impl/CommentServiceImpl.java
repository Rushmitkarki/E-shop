package com.ecommerce.ecommerce.service.impl;


import com.ecommerce.ecommerce.dto.CommentDto;
import com.ecommerce.ecommerce.entity.Comment;
import com.ecommerce.ecommerce.repo.CommentRepo;
import com.ecommerce.ecommerce.service.CommentService;
import com.ecommerce.ecommerce.service.ItemService;
import com.ecommerce.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    final UserService userService;
    final ItemService itemService;
    final CommentRepo commentRepo;
    @Override
    public void addComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setCommentText(commentDto.getComment());
        comment.setItem(itemService.getByIdNoOpt(commentDto.getItemId()).get());
        comment.setUser(userService.getActiveUser().get());
        commentRepo.save(comment);
    }

    @Override
    public List<Comment> getCommentsByItemId(int itemId) {
        return commentRepo.getCommentsByItemId(itemId);
    }
}
