package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.BillDto;
import com.ecommerce.ecommerce.entity.Bill;
import com.ecommerce.ecommerce.entity.Cart;

import java.util.List;
import java.util.Optional;

public interface BillService {

    void saveBill(BillDto billDto, List<Cart> carts);

    Optional<Bill> getByIdNoOpt(Integer id);

    int getLatestBillId();
}
