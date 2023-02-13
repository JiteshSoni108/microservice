package com.user.service.UserService.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hotel {
    private String hotelID;
    private String hotelName;
    private String location;
}
