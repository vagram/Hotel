package com.example.hotel.service;

import com.example.hotel.entity.Room;
import com.example.hotel.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImp implements RoomService{
    @Override
    public List<Room> getAllRooms() {
        return null;
    }

    @Override
    public Room getRoomByName(String name) {
        return null;
    }

    @Override
    public Room makeReservation(User user) {
        return null;
    }

    @Override
    public Room cancelReservation(User user) {
        return null;
    }

    @Override
    public Room dateReservationOn(User user) {
        return null;
    }

    @Override
    public Room dateReservationOff(User user) {
        return null;
    }
}
