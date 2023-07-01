package com.ecommerce.ecommerce.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentDto {
    private int id;
    private String comment;
    private int itemId;
    private int userId;
}
