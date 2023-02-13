package com.user.service.UserService.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="USERS")
public class User {
    @Id
    @Column(name = "ID")
    private String userID;
    @Column(name = "FULL_NAME", length = 20)
    private String fullName;
    @Column(name = "EMAIL")
    private String email;

    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @Transient
    private List<Rating> rating;
}
