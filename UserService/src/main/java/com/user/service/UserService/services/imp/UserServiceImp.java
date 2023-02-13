package com.user.service.UserService.services.imp;

import com.user.service.UserService.config.HotelServiceFeignClient;
import com.user.service.UserService.config.RatingServiceFeignClient;
import com.user.service.UserService.entities.Rating;
import com.user.service.UserService.entities.User;
import com.user.service.UserService.repositories.UserRepository;
import com.user.service.UserService.services.UserServices;
import com.user.service.UserService.services.exceptions.ResourceNotFoundExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RatingServiceFeignClient ratingServiceFeignClient;

    @Autowired
    private HotelServiceFeignClient hotelServiceFeignClient;

    public UserServiceImp() {
    }

    @Override
    public User saveUser(User user) {
        String userId=UUID.randomUUID().toString();
        user.setUserID(userId);
        user.getRating().stream().forEach(ratingDt->{
            ratingDt.setUserId(userId);
            ratingServiceFeignClient.createRating(ratingDt);
        });
        User outPut=userRepository.save(user);
        return getRatingsByUser(outPut);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        User output= userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundExceptions("Resource not found on server "+ userId));
        return getRatingsByUser(output);
    }

    private User getRatingsByUser(User output) {
        ResponseEntity<List<Rating>> ratingList=ratingServiceFeignClient.getRatingByUserId(output.getUserID());
        List<Rating> rateDt=ratingList.getBody().stream().map(rating ->{
            rating.setHotel(hotelServiceFeignClient.getHotelByHotelId(rating.getHotelId()));
            return rating;
        }).collect(Collectors.toList());
        output.setRating(rateDt);
        return output;
    }


}
