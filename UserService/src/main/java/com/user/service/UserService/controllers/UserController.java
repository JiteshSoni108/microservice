package com.user.service.UserService.controllers;

import com.user.service.UserService.entities.User;
import com.user.service.UserService.services.UserServices;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserServices userServices;

    private int retryCount=0;

    @PostMapping
    @CircuitBreaker(name = "RATING_SAVE_SERVICE", fallbackMethod = "ratingSaveFallBack")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1=userServices.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    public ResponseEntity<User> ratingSaveFallBack(@RequestBody User user , Exception ex){
        log.info("FallBack is executed because rating save service is down {} ,: "+ex.getMessage());
        return ResponseEntity.ok(User.builder().email("saveFallBack@saveFallBack.com").fullName("saveFallBack").build());
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @Retry(name="RATING_FETCH_RETRY_SERVICE" , fallbackMethod = "ratingFetchRetryFallBack")
    //@RateLimiter(name="RATING_FETCH_RATE_LIMITER" , fallbackMethod = "ratingFetchRetryFallBack") - Test using Jmeter.
    public ResponseEntity<User> getUserById(@PathVariable String userId){
        User user1=userServices.getUserById(userId);
        retryCount++;
        log.info("retryCunt on RATING service not present {}" ,retryCount);
        return ResponseEntity.ok(user1);
    }

    public ResponseEntity<User> ratingFetchRetryFallBack(@RequestBody User user , Exception ex){
        log.info("FallBack is executed because rating save service is down {} ,: "+ex.getMessage());
        return ResponseEntity.ok(User.builder().email("ratingFetchRetryFallBack@ratingFetchRetryFallBack.com").fullName("ratingFetchRetryFallBack").build());
    }
    @GetMapping()
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    public ResponseEntity<List<User>> getAllUser(){
        log.info("in getAllUser");
        List<User> user1=userServices.getAllUser();
        return ResponseEntity.ok(user1);
    }
}
