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

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    final RatingRepo ratingRepo;
    final UserService userService;
    final ItemService itemService;
    @Override
    public void saveRating(RatingDto ratingDto) {
        User user=userService.getActiveUser().get();
        Item item=itemService.getByIdNoOpt(ratingDto.getItemId()).get();
        Rating rating=ratingRepo.getByItemIdAndUserId(ratingDto.getItemId(),user.getUserId()).orElse(new Rating());
        rating.setRating(ratingDto.getRating());
        rating.setItem(item);
        rating.setUser(user);
        ratingRepo.save(rating);


    }

    @Override
    public Optional<Double> getAverageRating(int itemId) {

        return Optional.ofNullable(ratingRepo.getAverageRating(itemId));
    }
}
