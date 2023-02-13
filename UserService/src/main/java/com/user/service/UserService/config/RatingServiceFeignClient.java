package com.user.service.UserService.config;

import com.user.service.UserService.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="RATING-SERVICE")
public interface RatingServiceFeignClient {
    @GetMapping("/ratings/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable("userId") String userId);

    @PostMapping("/ratings")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating);
}