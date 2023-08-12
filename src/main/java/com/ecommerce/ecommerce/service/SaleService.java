package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.entity.Sales;
import com.ecommerce.ecommerce.entity.User;

import java.util.List;

public interface SaleService {

    void saveSale(User user);

    List<Sales> pastSevenDaysSales(User user);

    int getSalesCount(User user);
}
