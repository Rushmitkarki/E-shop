package com.ecommerce.ecommerce.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private String subAmount;
    private double discount;
    private double tax;
    private double shipping;
    private double billAmount;
    private String billDate;

}
