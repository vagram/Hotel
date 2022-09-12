package com.example.hotel.controller;

import com.example.hotel.entity.Booking;
import com.example.hotel.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/booking/")
    public List<Booking> findBookingsByActiveBookingFalse(){
        if (!bookingService.findBookingsByActiveBookingFalse().isEmpty()) {
            return bookingService.findBookingsByActiveBookingFalse();
        } else throw new RuntimeException("No Active Booking(s)");
    }
    @GetMapping("/booking/room/{roomId}")
    public Booking findBookingByRoomId(@PathVariable Integer roomId){
        return bookingService.findBookingByRoomId(roomId);
    }
    @GetMapping("/booking/user/{userId}")
    public List<Booking> findBookingByUserId(@PathVariable Integer userId){
        return bookingService.findBookingByUserId(userId);
    }
    @PostMapping("/bookingPost/")
    public Booking bookingRoom(@RequestBody Booking booking){
        return bookingService.bookingRoom(booking);
    }
}
