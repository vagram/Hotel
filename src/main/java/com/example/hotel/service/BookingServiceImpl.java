package com.example.hotel.service;

import com.example.hotel.entity.Booking;
import com.example.hotel.entity.Room;
import com.example.hotel.entity.User;
import com.example.hotel.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RoomService roomService;
    @Autowired
    private UserService userService;


    @Override

    public Booking bookingRoom(Booking booking) {
        User user = userService.getById(booking.getUserId()).orElse(null);
        Room room = roomService.getRoomById(booking.getRoomId());

        LocalDate startDate = booking.getCheckIn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = booking.getCheckOut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        int numbOfDays = startDate.compareTo(endDate);
        int bookingPrice = room.getPrice() * numbOfDays;


        if (user.getAccountBalance() < bookingPrice || booking.getSoftDelBook() != null) {
            throw new RuntimeException("Not enough money or booking active");
        } else if (bookingValidation(booking)) {
            user.setAccountBalance(user.getAccountBalance() + bookingPrice);
            booking.setActiveBooking(false);
            bookingRepository.save(booking);
            userService.updateUser(user);
            return booking;
        } else {
            throw new RuntimeException("These dates are not available");
        }
    }
    @Override
    public boolean bookingValidation(Booking booking) {
        List<Booking> list = bookingRepository.findBookingsByRoomId(booking.getRoomId())
                .stream()
                .filter(booking1 -> booking1.isActiveBooking() == false)
                .filter(booking1 -> booking.getCheckIn().equals(booking1.getCheckIn()) && booking.getCheckOut().equals(booking1.getCheckOut())
                        || booking.getCheckIn().before(booking1.getCheckIn()) && booking.getCheckOut().after(booking1.getCheckOut())
                        || booking.getCheckIn().before(booking1.getCheckIn()) && booking.getCheckOut().after(booking1.getCheckIn())
                        || booking.getCheckIn().before(booking1.getCheckOut()) && booking.getCheckOut().after(booking1.getCheckOut())
                        || booking.getCheckIn().after(booking1.getCheckIn()) && booking.getCheckOut().before(booking1.getCheckOut())
                        || booking.getCheckIn().equals(booking1.getCheckOut()) && booking.getCheckOut().after(booking1.getCheckOut())
                        ||booking.getCheckIn().before(booking1.getCheckIn()) && booking.getCheckOut().equals(booking1.getCheckIn()))
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            return true;
        } else {
            throw new RuntimeException("Not available dates");
        }
    }

    @Override
    public List<Booking> findBookingByUserId(Integer userId) {
        List<Booking> bookingList = bookingRepository.findBookingsByUserId(userId);
        return bookingList;
    }

    @Override
    public Booking findBookingByRoomId(Integer roomId) {
        Booking booking = bookingRepository.findBookingByRoomId(roomId);
        return booking;
    }

    @Override
    public void cancelBooking(Integer id) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            booking.setSoftDelBook(new Date());
            bookingRepository.save(booking);
        }
    }

    @Override
    public List<Booking> findBookingsByActiveBookingFalse() {
        if (!bookingRepository.findBookingsByActiveBookingFalse().isEmpty()) {
            return bookingRepository.findBookingsByActiveBookingFalse();

        } else {
            throw new RuntimeException("No Active Booking(s)");
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
        bookingRepository.findAll().forEach(e -> e.setSoftDelBook(new Date()));
    }
}
