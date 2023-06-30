package com.ecommerce.ecommerce.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RatingDto {

    private int itemId;
    private String userId;

    private int rating;
}
