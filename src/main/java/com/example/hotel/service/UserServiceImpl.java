package com.example.hotel.service;

import com.example.hotel.entity.User;
import com.example.hotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public Optional<User> getById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User makeReservation(Integer id) {

        return null;
    }

    @Override
    public User seeReservationByIdReservation(Integer idReservation) {
        return null;
    }

    @Override
    public User cancelReservationByIdReservation(Integer idReservation) {
        return null;
    }

    @Override
    public User cancelAllReservations() {
        return null;
    }

    @Override
    public User userDelete(User id) {
        return null;
    }

    @Override
    public void deleteAllUsers() {

    }
}
