package com.example.msshop.service;

import java.util.Optional;
import java.util.List;

import com.example.msshop.model.User;


public interface UserService {
    User login(String username, String password);
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id,User user);
    void deleteUser(Long id);
}
