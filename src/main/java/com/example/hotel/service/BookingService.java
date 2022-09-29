package com.example.hotel.service;

import com.example.hotel.entity.Booking;

import java.util.List;


public interface BookingService {

    Booking bookingRoom(Booking booking);

    boolean bookingValidation(Booking booking);

    List<Booking> findBookingByUserId(Integer userId);
    Booking findBookingByRoomId(Integer roomId);
    void cancelBooking(Integer id);
    List<Booking> findBookingsByActiveBookingFalse();
    List<Booking> getAllBooking();
    List<Booking> getAllBookingsByRoomId(Integer roomId);
    void cancelAllBooking();
}
