package com.example.hotel.service;

import com.example.hotel.entity.Room;
import com.example.hotel.entity.User;

import java.util.List;

public interface RoomService {
    List<Room> getAllRooms();
    Room getRoomByName(String name);
    Room makeReservation(User user);
    Room cancelReservation(User user);
    Room dateReservationOn(User user);
    Room dateReservationOff(User user);
}
