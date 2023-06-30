package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.RatingDto;
import com.ecommerce.ecommerce.entity.Item;
import com.ecommerce.ecommerce.entity.Rating;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.repo.RatingRepo;
import com.ecommerce.ecommerce.service.ItemService;
import com.ecommerce.ecommerce.service.RatingService;
import com.ecommerce.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    final RatingRepo ratingRepo;
    final UserService userService;
    final ItemService itemService;
    @Override
    public void saveRating(RatingDto ratingDto) {
        Rating rating=new Rating();
        User user=userService.getActiveUser().get();
        rating.setRating(ratingDto.getRating());
        Item item=itemService.getByIdNoOpt(ratingDto.getItemId()).get();
        rating.setItem(item);
        rating.setUser(user);


    }
}
