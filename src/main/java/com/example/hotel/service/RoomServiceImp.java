package com.example.hotel.service;

import com.example.hotel.entity.Room;
import com.example.hotel.entity.User;
import com.example.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServiceImp implements RoomService{
    @Autowired
    private RoomRepository roomRepository;
    @Override
    public List<Room> getAllRooms() {
        List<Room> roomList = roomRepository.findAll();
        return roomList;
    }

    @Override
    public Room getRoomById(Integer id) {
        Room room = roomRepository.findById(id).orElse(null);
        return room;
    }

    @Override
    public Room getRoomByName(String name) {
        Room room = roomRepository.findByName(name);
        return room;
    }

    @Override
    public List<Room> getAllEnabled() {
        List<Room> roomList = roomRepository.findAllByEnableIsTrue();
        return roomList;

    }

    @Override
    public Room saveOrUpload(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void deleteById(Integer id) {
        Optional<Room> room = roomRepository.findById(id);
        room.ifPresent(value -> value.setSoftDelRoom(new Date()));
    }

    @Override
    public void deleteAllRooms() {
        List<Room> roomList = roomRepository.findAll();
        if (!roomList.isEmpty()){
            roomList.forEach(room -> room.setSoftDelRoom(new Date()));
        }
    }



}
