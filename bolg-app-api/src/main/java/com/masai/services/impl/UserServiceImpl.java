package com.masai.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entities.User;
import com.masai.exceptions.ResourceNotFoundException;
import com.masai.payloads.UserDto;
import com.masai.repository.UserRepo;
import com.masai.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo repo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.repo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {

		User user = this.repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		user.setMobile(userDto.getMobile());

		User updatedUser = this.repo.save(user);

		UserDto userDto1 = this.userToDto(updatedUser);

		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {

		User user = this.repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		UserDto userDto = this.userToDto(user);

		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> userList = this.repo.findAll();

		List<UserDto> userDtoList = userList.stream().map((user) -> this.userToDto(user)).collect(Collectors.toList());

		return userDtoList;
	}

	@Override
	public String deleteUser(Integer userId) {

		User user = this.repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		this.repo.delete(user);
		return "User Deleted that id is  " + userId;
	}

	/// model mapper class dependency
	public User dtoToUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}

	// model mapper manually
//	public User dtoToUser(UserDto userDto) {
//
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
//		user.setMobile(userDto.getMobile());
//		return user;
//
//	}

	public UserDto userToDto(User user) {

		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

//	public UserDto userToDto(User user) {
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
//		user.setMobile(userDto.getMobile());
//		return userDto;
//
//	}

}
