package com.example.msshop.service;

import java.util.Optional;

import com.example.msshop.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id,User user);
    void deleteUser(Long id);
}
