package com.example.hotel.repository;

import com.example.hotel.entity.Booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Booking findBookingByRoomId(Integer roomId);
    List<Booking> findBookingsByUserId(Integer userId);
    Booking deleteBookingByRoomId(Integer roomId);
    List<Booking> findBookingsByActiveBookingFalse();
    List<Booking> findBookingsByRoomId(Integer roomId);
    @Query(value = "select * from booking where soft_delete_book is null or soft_delete_book is not null ", nativeQuery = true)
    List<Booking> toGetAllBookingsByAdmin();





}
