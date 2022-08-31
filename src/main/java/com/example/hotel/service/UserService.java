package com.example.hotel.service;

import com.example.hotel.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getById(Integer id);
    User updateUser(User user);
    void userDeleteById(Integer id);
    void deleteAllUsers();
    User createUser(User user);
    Optional<User> getUserAccountBalance(User userId);

}
