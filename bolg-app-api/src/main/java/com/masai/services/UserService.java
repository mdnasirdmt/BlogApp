package com.masai.services;

import java.util.List;

import com.masai.dtoClass.UserDto;

public interface UserService {

	UserDto createUser(UserDto user);

	UserDto updateUser(UserDto user, Integer userId);

	UserDto getUserById(Integer userId);

	List<UserDto> getAllUsers();

	String deleteUser(Integer userId);

}
