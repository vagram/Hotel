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
    public User createUser(User user) {
        //needs to change method when add Roles
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserAccountBalance(User userId) {
        Optional<User> user1 = userRepository.findById(userId.getAccountBalance());
        return user1;
    }

    @Override
    public Optional<User> getById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void userDeleteById(Integer id) {
        //Needs to do soft delete later
        userRepository.deleteById(id);
    }


    @Override
    public void deleteAllUsers() {
        //Needs to do soft delete later
        userRepository.deleteAll();
    }


}
