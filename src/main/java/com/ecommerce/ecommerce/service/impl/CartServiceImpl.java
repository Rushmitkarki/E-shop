package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.CartDto;
import com.ecommerce.ecommerce.entity.Cart;
import com.ecommerce.ecommerce.entity.Item;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.repo.CartRepo;
import com.ecommerce.ecommerce.service.CartService;
import com.ecommerce.ecommerce.service.ItemService;
import com.ecommerce.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final UserService userService;

    private final ItemService itemService;

    private final CartRepo cartRepo;
    @Override
    public void addCart(CartDto cartDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.getByEmail(email).orElse(new User());
        Item item = itemService.getByIdNoOpt(cartDto.getItemId()).orElse(null);
        Cart cart = new Cart();

        int Quantity = cartDto.getQuantity();
        int price = item.getItemPrice();
        cart.setQuantity(cartDto.getQuantity());
        cart.setItem(item);
        cart.setUser(user);
        cart.setTotal(Quantity*price);

        cartRepo.save(cart);

    }
}
