package com.rating.service.controllers;

import com.rating.service.entities.Rating;
import com.rating.service.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        Rating rating1=ratingService.create(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating1);
    }

    @GetMapping("/{ratingId}")
    @PreAuthorize("hasAuthority('SCOPE_internal')")
    public ResponseEntity<Rating> getRatingById(@PathVariable String ratingId){
        Rating rating=ratingService.getRatingByRatingID(ratingId);
        return ResponseEntity.ok(rating);
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
        List<Rating> rating=ratingService.getRatingByHotelID(hotelId);
        return ResponseEntity.ok(rating);
    }

    @GetMapping("/users/{userId}")
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
        List<Rating> rating=ratingService.getRatingByUserID(userId);
        return ResponseEntity.ok(rating);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    public ResponseEntity<List<Rating>> getAllUser(){
        List<Rating> rating=ratingService.getAllRating();
        return ResponseEntity.ok(rating);
    }
}
