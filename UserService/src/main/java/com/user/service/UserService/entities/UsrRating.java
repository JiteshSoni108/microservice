package com.user.service.UserService.entities;

import jakarta.persistence.*;
import lombok.*;

//@Entity
//@Table(name = "USER_RATING")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsrRating {

    private String ratingID;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedBack;



}