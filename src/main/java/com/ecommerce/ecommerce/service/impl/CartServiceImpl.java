package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.CartDto;
import com.ecommerce.ecommerce.entity.Bill;
import com.ecommerce.ecommerce.entity.Cart;
import com.ecommerce.ecommerce.entity.Item;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.repo.BillRepo;
import com.ecommerce.ecommerce.repo.CartRepo;
import com.ecommerce.ecommerce.repo.ItemRepo;
import com.ecommerce.ecommerce.service.CartService;
import com.ecommerce.ecommerce.service.ItemService;
import com.ecommerce.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final UserService userService;

    private final ItemService itemService;

    private final CartRepo cartRepo;

    private final BillRepo billRepo;

    private final ItemRepo itemRepo;
    @Override
    public void addCart(CartDto cartDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.getByEmail(email).orElse(new User());
        Item item = itemService.getByIdNoOpt(cartDto.getItemId()).get();
        Cart cart = new Cart();
        int quantity = cartDto.getQuantity();
        int price = item.getItemPrice();
        int itemQuantity = item.getItemQuantity() - quantity;
        item.setItemQuantity(itemQuantity);
        cart.setQuantity(quantity);
        cart.setItem(item);
        cart.setUser(user);
        cart.setStatus("unPaid");
        cart.setTotal(quantity*price);

        itemRepo.save(item);
        cartRepo.save(cart);

    }

    @Override
    public List<Cart> getDataByUserId(int UserId) {
        return cartRepo.findByUserId(UserId);
    }

    @Override
    public void deleteCart(int id) {
        cartRepo.deleteById(id);
    }

    @Override
    public void setStatus(Cart cart) {
        cart.setStatus("Paid");
        cartRepo.save(cart);
    }

    @Override
    public List<Cart> getCartByBillId(int billId) {
        return cartRepo.findByBillId(billId);
    }

    @Override
    public void addBillId(Bill bill, Cart cart) {
        cart.setBill(bill);
        cartRepo.save(cart);
    }


}
