package com.example.hotel.service;

import com.example.hotel.entity.Room;


import java.util.List;

public interface RoomService {
    List<Room> getAllRooms();
    Room getRoomById(Integer id);
    Room getRoomByName(String name);
    List<Room> getAllEnabled();
    Room saveOrUpload(Room room);
    void deleteById(Integer id);
    void deleteAllRooms();

    //todo create method getEnable room from List of rooms
}
