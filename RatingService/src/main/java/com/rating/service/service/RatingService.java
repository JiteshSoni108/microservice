package com.rating.service.service;

import com.rating.service.entities.Rating;

import java.util.List;

public interface RatingService {

    Rating create(final Rating rating);
    Rating getRatingByRatingID(final String ratingID);
    List<Rating> getAllRating();
    List<Rating> getRatingByUserID(final String userID);
    List<Rating> getRatingByHotelID(final String hotelId);
}
