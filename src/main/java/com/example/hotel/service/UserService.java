package com.example.hotel.service;

import com.example.hotel.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getById(Integer id);
    void updateUser(User user);
    User makeReservation(Integer id);
    User seeReservationByIdReservation(Integer idReservation);
    User cancelReservationByIdReservation(Integer idReservation);
    User cancelAllReservations();
    User userDelete(User id);
    void deleteAllUsers();

}
