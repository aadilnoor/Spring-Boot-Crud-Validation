package com.crud.controller;

import java.util.HashMap;  
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crud.dto.UserDto;
import com.crud.entity.User;
import com.crud.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	

	@PostMapping("/saveUser")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto, BindingResult result) {
		
		if (result.hasErrors()) {
			Map<String, String> map = new HashMap<String, String>();
			for (FieldError error : result.getFieldErrors()) {
				map.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}
		this.userService.saveUser(userDto);

		/*
		 * User user = new User(); user.setName(userDto.getName());
		 * user.setAddress(userDto.getAddress()); user.setEmail(userDto.getEmail());
		 * user.setAge(userDto.getAge());
		 */
		
		return new ResponseEntity<>("User is Valid & saved successfully", HttpStatus.OK);
	}

	@GetMapping("/allUsers")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return ResponseEntity.ok(this.userService.getAllUsers());	 
	}

	@GetMapping("/getUser/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
		return ResponseEntity.ok( userService.getUserById(id));
		
	}

	@PutMapping("/updateUser/{id}")
	public ResponseEntity<String> upadteUser(@RequestBody UserDto userDto, @PathVariable Integer id) {
		userService.updateUserById(userDto, id);
		return new ResponseEntity<>("User Updated Succcessfully..", HttpStatus.OK);

	}

	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Integer id) {
		userService.deleteUserById(id);
		return new ResponseEntity<>("User deleted Succcessfully...", HttpStatus.OK);
	}
	@GetMapping("/getByName")
	public ResponseEntity<List<UserDto>> getByName(@RequestParam String name){
		return ResponseEntity.ok(this.userService.getByName(name));
		
	}
}
