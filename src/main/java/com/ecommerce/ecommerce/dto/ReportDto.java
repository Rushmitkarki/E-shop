package com.ecommerce.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReportDto{
    private int id;
    private String reportDescription;
    private int userId;
    private int itemId;
}