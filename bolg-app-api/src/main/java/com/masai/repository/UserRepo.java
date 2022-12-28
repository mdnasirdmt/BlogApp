package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
