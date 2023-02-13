package com.user.service.UserService.config;

import com.user.service.UserService.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="HOTEL-SERVICE")
public interface HotelServiceFeignClient {

    @GetMapping("/hotels/{hotelId}")
    public Hotel getHotelByHotelId(@PathVariable("hotelId") String hotelId);
}
