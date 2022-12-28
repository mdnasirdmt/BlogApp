package com.masai.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.masai.entities.User;
import com.masai.payloads.UserDto;
import com.masai.repository.UserRepo;
import com.masai.services.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo repo;

	@Override
	public UserDto createUser(UserDto user) {

		return null;
	}

	@Override
	public UserDto updateUser(UserDto user, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	private User dtoToUser(UserDto userDto) {

		User user = new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		return user;

	}

}
