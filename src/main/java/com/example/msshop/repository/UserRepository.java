package com.example.msshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.msshop.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}