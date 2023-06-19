package com.ecommerce.ecommerce.dto;

import com.ecommerce.ecommerce.entity.Category;
import jakarta.mail.Multipart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

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

    private MultipartFile itemImage;


    private String email;


}
