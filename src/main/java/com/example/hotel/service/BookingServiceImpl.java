package com.example.hotel.service;

import com.example.hotel.entity.Booking;
import com.example.hotel.entity.Room;
import com.example.hotel.entity.User;
import com.example.hotel.repository.BookingRepository;
import com.example.hotel.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Override

    public Booking bookingRoom(Booking booking) {
        User user = userService.getById(booking.getUserId()).orElse(null);
        Room room = roomService.getRoomById(booking.getRoomId());
        List<Booking> bookingList = bookingService.getAllBookingsByRoomId(booking.getRoomId()).stream()
                .filter(Booking::isActiveBooking).collect(Collectors.toList());

        int numbOfDays = Integer.parseInt(String.valueOf(booking.getCheckOut())) - Integer.parseInt(String.valueOf(booking.getCheckIn()));
        int bookingPrice = room.getPrice() * numbOfDays;
        if (!booking.isActiveBooking() && booking.getSoftDelBook() == null && booking.getUserId()!= null || bookingList == null) {
            if (user.getAccountBalance() >= bookingPrice) {
                for (Booking bookingExist : bookingList) {
                    if (booking.getCheckIn().before(booking.getCheckOut())) {
                        if ((booking.getCheckIn().before(bookingExist.getCheckIn())) && (booking.getCheckOut().before(bookingExist.getCheckIn()))) {
                            int userNewAccountBal = user.getAccountBalance() - bookingPrice;
                            user.setAccountBalance(userNewAccountBal);
                            booking.setActiveBooking(booking.isActiveBooking());
                            return booking;
                        } else if ((booking.getCheckIn().after(bookingExist.getCheckOut())) && (booking.getCheckOut().before(bookingExist.getCheckIn()))) {
                            booking.setActiveBooking(booking.isActiveBooking());
                            return booking;
                        } else {
                            throw new RuntimeException("At these dates that room already booked");
                        }
                    } else {
                        throw new RuntimeException("Check In date should be earlier then Check Out date");
                    }
                }
            } else {
                throw new RuntimeException("Not enough money on the Account Balance");
            }
        } else {
            throw new RuntimeException("(Booking is not active or deleted) or (this userId doesn't exist");
        }
        return null;
    }

    @Override
    public List<Booking> getBookingByUserId(Integer userId) {
        List<Booking> bookingList = bookingRepository.findBookingsByUserId(userId);
        return bookingList;
    }

    @Override
    public Booking getBookingById(Integer roomId) {
        Booking booking = bookingRepository.findBookingByRoomId(roomId);
        return booking;
    }

    @Override
    public void cancelBooking(Integer id) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null){
            booking.setSoftDelBook(new Date());
            bookingRepository.save(booking);
        }
    }

    @Override
    public List<Booking> findBookingsByActiveBookingTrue() {
        if (!bookingRepository.findBookingsByActiveBookingTrue().isEmpty()){
            return bookingRepository.findBookingsByActiveBookingTrue();

        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Booking> getAllBooking() {
        List<Booking> bookingList = bookingRepository.toGetAllBookingsByAdmin();
        return bookingList;
    }

    @Override
    public List<Booking> getAllBookingsByRoomId(Integer roomId) {

        return bookingRepository.findBookingsByRoomId(roomId);
    }

    @Override
    public void cancelAllBooking() {
        bookingRepository.findAll().forEach(e->e.setSoftDelBook(new Date()));
    }
}
