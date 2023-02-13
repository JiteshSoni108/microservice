package com.rating.service.service.imp;

import com.rating.service.entities.Rating;
import com.rating.service.repository.RatingRepository;
import com.rating.service.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImp implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;
    @Override
    public Rating create(Rating rating) {
        rating.setRatingID(UUID.randomUUID().toString());
        return ratingRepository.save(rating);
    }

    @Override
    public Rating getRatingByRatingID(String ratingID) {
        return ratingRepository.findById(ratingID).orElseThrow();
    }

    @Override
    public List<Rating> getAllRating() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserID(String userID) {
        return ratingRepository.findByUserId(userID);
    }

    @Override
    public List<Rating> getRatingByHotelID(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
