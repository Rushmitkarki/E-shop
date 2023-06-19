package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.BillDto;
import com.ecommerce.ecommerce.entity.Bill;

import java.util.Optional;

public interface BillService {

    void saveBill(BillDto billDto);

    Optional<Bill> getByIdNoOpt(Integer id);
}
