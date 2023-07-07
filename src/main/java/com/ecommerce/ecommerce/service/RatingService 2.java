package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.RatingDto;

import java.util.Optional;

public interface RatingService {

    void saveRating(RatingDto ratingDto);

    Optional<Double> getAverageRating(int itemId);
}
