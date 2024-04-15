package com.example.msshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.msshop.model.User;
import com.example.msshop.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User createUser(User user){
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user){
        if(userRepository.existsById(id)){
            user.setId(id);
            return userRepository.save(user);
        } 
        return null;
    }

    @Override
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

}
