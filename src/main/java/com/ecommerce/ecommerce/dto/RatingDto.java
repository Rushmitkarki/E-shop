package com.ecommerce.ecommerce.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class RatingDto {

    private int itemId;
    private String userId;

    private double rating;
}
