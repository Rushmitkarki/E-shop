package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.NotificationDto;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.entity.Discount;
import com.ecommerce.ecommerce.entity.Notification;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.repo.NotificationRepo;
import com.ecommerce.ecommerce.service.DiscountService;
import com.ecommerce.ecommerce.service.NotificationService;
import com.ecommerce.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepo notificationRepo;
    private final DiscountService discountService;
    private final UserService userService;

    @Override
    public void addNotification() {
        Notification notification = new Notification();
        discountService.addDiscount();
        List<Discount> discounts = discountService.getData();
        Discount discount=getRandomDiscount(discounts);
        String message = "Discount code "+discount.getDiscountCode()+" is active for "+discount.getCategory().getCatName()+" category with "+discount.getDiscountPercentage()+"% discount";
        notification.setNotifyContent(message);
        notification.setUser(getRandomUser());
        notificationRepo.save(notification);

        }

    private Discount getRandomDiscount(List<Discount> discounts) {
        int index = (int) (Math.random() * discounts.size());
        Discount discount = discounts.get(index);
        if(discount.getDiscountStatus().equals("active")){
            return discount;
        }
        else {
            return getRandomDiscount(discounts);
        }
    }

    private User getRandomUser() {
        List<User> users = userService.getData();
        int index = (int) (Math.random() * users.size());
        return users.get(index);
    }

    @Override
    public List<Notification> getData() {

        return notificationRepo.findAll();
    }

    @Override
    public Notification getByIdNoOpt(Integer id) {

        return notificationRepo.findById(id).orElse(null);
    }

    @Override
    public List<Notification> getNotification() {
        int userId=userService.getActiveUser().get().getUserId();
        return notificationRepo.getByUserId(userId);
    }
}
