package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.entity.Cart;
import com.ecommerce.ecommerce.entity.Item;
import com.ecommerce.ecommerce.entity.Sales;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.repo.SaleRepo;
import com.ecommerce.ecommerce.service.SaleService;
import com.ecommerce.ecommerce.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepo saleRepo;
    private final UserService userService;
    private final CartService cartService;
    private final BillService billService;
    private final ItemService itemService;
    @Override
    public void saveSale(User user) {

        List<Item> items = itemService.getBySellerId(user.getUserId());
        for(Item item: items){
            List<Cart> carts = cartService.getByItemIdAndStatus(item.getItemId());
            for(Cart cart: carts){
                LocalDate date = cart.getDate();
                Sales sales = saleRepo.getByDateAndUser(date,user.getUserId() ).orElse(new Sales());
                sales.setSeller(user);
                sales.setDate(date);
                int quantity = sales.getQuantity() + cart.getQuantity();
                sales.setQuantity(quantity);
                double total = sales.getTotal() + cart.getTotal();
                sales.setTotal(total);
                saleRepo.save(sales);
            }
        }
    }

    @Override
    public List<Sales> pastSevenDaysSales(User user) {
        LocalDate date = LocalDate.now();
        LocalDate date1 = date.minusDays(7);
        return saleRepo.findByDateBetweenAndSeller(date1,date,user.getUserId());
    }

    @Override
    public int getSalesCount(User user) {
        List<Sales> sales = saleRepo.findBySeller(user.getUserId());
        int qty=0;
        for(Sales sale: sales){
            qty += sale.getQuantity();
        }
        return qty;
    }
}
