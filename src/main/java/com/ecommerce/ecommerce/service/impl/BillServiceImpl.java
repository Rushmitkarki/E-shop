package com.ecommerce.ecommerce.service.impl;


import com.ecommerce.ecommerce.dto.BillDto;
import com.ecommerce.ecommerce.entity.Bill;
import com.ecommerce.ecommerce.entity.Cart;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.repo.BillRepo;
import com.ecommerce.ecommerce.service.BillService;
import com.ecommerce.ecommerce.service.CartService;
import com.ecommerce.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
    private final BillRepo billRepo;

    private final UserService userService;

    private final CartService cartService;
    @Override
    public void saveBill(BillDto billDto, List<Cart> carts) {
        Bill bill = new Bill();
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        bill.setBillDate(formattedDateTime);

        double billSubAmount = billDto.getBillSubAmount();
        bill.setBillSubAmount(billSubAmount);
        double discount = billDto.getDiscount();
        bill.setDiscount(discount);
        double tax= billSubAmount*0.1;
        bill.setTax(tax);
        double shipping = billSubAmount*0.02;
        bill.setShipping(shipping);
        double billAmount = billSubAmount+tax+shipping-discount;
        bill.setBillAmount(billAmount);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.getByEmail(email).orElse(new User());
        bill.setUser(user);

        bill.setBillStatus("Pending");
        bill.setBillAddress(billDto.getBillAddress());
        bill.setBillPayment(billDto.getBillPayment());
        billRepo.save(bill);
        for(Cart cart: carts){
            cartService.setStatusTime(cart);
            cartService.addBillId(bill, cart);
        }
    }

    @Override
    public Optional<Bill> getByIdNoOpt(Integer id) {
        return billRepo.findById(id);
    }

    @Override
    public int getLatestBillId() {
        Bill latestBill = billRepo.findTopByOrderByIdDesc().orElse(new Bill());
        return latestBill.getBillId();
    }
}
