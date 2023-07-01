package com.ecommerce.ecommerce.controller;


import com.ecommerce.ecommerce.dto.CommentDto;
import com.ecommerce.ecommerce.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/add")
    public String addComment(@Valid CommentDto commentDto){
        commentService.addComment(commentDto);
        return "redirect:/buyer/item/"+commentDto.getItemId();
    }
}
