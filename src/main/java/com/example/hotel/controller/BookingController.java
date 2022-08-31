package com.example.hotel.controller;

import com.example.hotel.entity.Booking;
import com.example.hotel.repository.BookingRepository;
import com.example.hotel.service.BookingService;
import com.example.hotel.service.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@Service
public class BookingController {
    @Autowired
    private BookingRepository bookingRepository;;
    @Autowired
    private BookingService bookingService;

    @GetMapping("/booking/")
    public List<Booking> findBookingsByActiveBookingTrue(){
        return bookingRepository.findBookingsByActiveBookingTrue();
    }
    @GetMapping("/booking/{roomId}")
    public Booking findBookingByRoomId(@PathVariable Integer roomId){
        return bookingRepository.findBookingByRoomId(roomId);
    }
    @GetMapping("/booking/{userId}")
    public List<Booking> getBookingByUserId(@PathVariable Integer userId){
        return bookingRepository.findBookingsByUserId(userId);
    }
    @PostMapping("/")
    public Booking bookingRoom(@RequestBody Booking booking){
        return bookingService.bookingRoom(booking);
    }
}
