package com.ecommerce.ecommerce.dto;

import com.ecommerce.ecommerce.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private Integer itemId;
    private String itemName;
    private Integer itemPrice;
    private Integer itemQuantity;
    private String itemDescription;
    private int category;


}
