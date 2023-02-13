package com.hotel.service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="HOTEL_DT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hotel {

    @Id
    @Column(name="HOTEL_ID")
    private String hotelID;
    @Column(name="HOTEL_NAME")
    private String hotelName;
    @Column(name="LOCATION")
    private String location;
    @Column(name="USER_ID")
    private String userId;
}
