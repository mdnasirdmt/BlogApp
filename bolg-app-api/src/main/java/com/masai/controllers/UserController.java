package com.masai.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entities.User;
import com.masai.payloads.ApiResponse;
import com.masai.payloads.UserDto;
import com.masai.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService service;

	// save user// http://localhost:9090/api/users/
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		UserDto createUserDto = this.service.createUser(userDto);

		return new ResponseEntity<UserDto>(createUserDto, HttpStatus.CREATED);
	}

	// update user// http://localhost:9090/api/users/1
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Integer userId) {
		UserDto user = this.service.updateUser(userDto, userId);
		return new ResponseEntity<UserDto>(user, HttpStatus.OK);
	}

	// delete user // http://localhost:9090/api/users/1
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {

		this.service.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted", true), HttpStatus.OK);
	}

	// get single user
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) {

		UserDto userDto = this.service.getUserById(userId);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}

	// get all user
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser() {

		List<UserDto> userDtoList = this.service.getAllUsers();
		return new ResponseEntity<List<UserDto>>(userDtoList, HttpStatus.OK);
	}

}
