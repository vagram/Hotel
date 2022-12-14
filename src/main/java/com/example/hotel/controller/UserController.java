package com.example.hotel.controller;

import com.example.hotel.entity.User;

import com.example.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Service
@Controller
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/users/")
    public List<User> getAllUsers(){

        return userService.getAllUsers();
    }

    @PostMapping("/user/create/")
    public User createUser(@RequestBody User user){

        return userService.createUser(user);
    }
    @GetMapping("/useraccountbalance/{userId}")
    public Optional<User> getUserAccountBalance(@PathVariable User userId){
        return userService.getUserAccountBalance(userId);
    }
    @GetMapping("/usergetbyid/{id}")
    public Optional<User> getById(@PathVariable Integer id){
        return userService.getById(id);
    }
    @PostMapping("/userupdate/{user}")
    public User updateUser(@PathVariable User user){
        return userService.updateUser(user);
    }
    @PostMapping("/userdeletebyid/{id}")
    public void userDeleteById(@PathVariable Integer id){
        userService.userDeleteById(id);
    }
    @PostMapping("/usersdelete")
    void deleteAllUsers(){
        userService.deleteAllUsers();
    }
}
