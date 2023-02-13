package com.user.service.UserService.services;

import com.user.service.UserService.entities.User;

import java.util.List;

public interface UserServices {

    User saveUser(User user);
    List<User> getAllUser();
    User getUserById(final String userId);
}
