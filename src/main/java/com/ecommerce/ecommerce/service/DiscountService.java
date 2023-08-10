package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.entity.Discount;

import java.util.List;

public interface DiscountService {

    void addDiscount();

    List<Discount> getData();
}
