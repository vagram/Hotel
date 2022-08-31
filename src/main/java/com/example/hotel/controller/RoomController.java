package com.example.hotel.controller;

import com.example.hotel.entity.Room;
import com.example.hotel.repository.RoomRepository;
import com.example.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Controller
@Service
public class RoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/rooms/")
    public List<Room> getAllRooms(){
        return roomService.getAllRooms();
    }
    @GetMapping("/room/{id}")
    public Room getRoomById(@PathVariable Integer id){
        return roomService.getRoomById(id);
    }
    @GetMapping("/room/{name}")
    public Room getRoomByName(@PathVariable String name){
        return roomService.getRoomByName(name);
    }
    @GetMapping("/roomenable/")
    public List<Room> getAllEnabled(){
        return roomService.getAllEnabled();
    }
    @PostMapping("/roomsave/{room}")
    public Room saveOrUpload(@PathVariable Room room){
        return roomService.saveOrUpload(room);
    }
    @PostMapping("/roomdelete/{id}")
    public void deleteById(@PathVariable Integer id){
        roomService.deleteById(id);
    }
    @PostMapping("/deleteallrooms/")
    public void deleteAllRooms(){
        roomService.deleteAllRooms();
    }

}
