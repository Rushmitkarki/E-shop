package com.ecommerce.ecommerce.dto;


import com.ecommerce.ecommerce.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {

    private Integer billId;
    private String email;
    private String billStatus;
    private Double billSubAmount;
    private String billAddress;

    private double discount;
    private double tax;
    private double shipping;
    private double billAmount;
    private String billDate;
    private String billPayment;


}
