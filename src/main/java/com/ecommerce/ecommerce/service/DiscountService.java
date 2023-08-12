package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.entity.Discount;

import java.util.List;
import java.util.Optional;

public interface DiscountService {

    void addDiscount();

    List<Discount> getData();

    Optional<Discount> getByCode(String code);

    void setDiscountStatus(String code);
}
