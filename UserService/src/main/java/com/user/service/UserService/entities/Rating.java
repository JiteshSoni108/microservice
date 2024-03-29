package com.user.service.UserService.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {
    private String ratingID;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedBack;
    private Hotel hotel;
}
