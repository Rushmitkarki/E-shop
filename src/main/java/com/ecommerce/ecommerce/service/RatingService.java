package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.RatingDto;

public interface RatingService {

    void saveRating(RatingDto ratingDto);

    double getAverageRating(int itemId);
}
