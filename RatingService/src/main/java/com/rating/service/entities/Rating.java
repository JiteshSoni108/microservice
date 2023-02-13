package com.rating.service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name ="RATING_DT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {

    @Id
    @Column(name="RATING_ID")
    private String ratingID;
    @Column(name="USER_ID")
    private String userId;
    @Column(name="HOTEL_ID")
    private String hotelId;
    @Column(name="RATING")
    private int rating;
    @Column(name="FEEDBACK")
    private String feedBack;

}
