package com.ecommerce.ecommerce.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private int id;
    private int itemId;
    private int userId;
    private int quantity;
    private double total;
}
