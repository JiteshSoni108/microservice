package com.hotel.service.services;

import com.hotel.service.entities.Hotel;

import java.util.List;

public interface HotelService{

    Hotel create(final Hotel hotel);
    List<Hotel> getAll();
    Hotel getHotelById(final String hotelId);
}
