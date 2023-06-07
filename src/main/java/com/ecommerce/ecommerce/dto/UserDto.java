package com.ecommerce.ecommerce.dto;

import lombok.*;
import jakarta.persistence.*;
import  jakarta.validation.constraints.*;
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

    private String role;
    private String status;
    private String citizenshipNumber;

    private String panNumber;

}
