package com.ecommerce.ecommerce.dto;

import com.ecommerce.ecommerce.entity.Item;
import lombok.*;
import jakarta.persistence.*;
import  jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer userId;
    @NotBlank(message = "User Name is mandatory")
    @NotNull(message = "User Name is mandatory")

    private String fname;
    private String lname;
    private String email;
    private String password;

    private List<Item> items;

    private String role;
    private String status;
    private String citizenshipNumber;

    private String panNumber;

    private String phoneNumber;

    private String address;

    private  String Sq;

    private MultipartFile image;
}
