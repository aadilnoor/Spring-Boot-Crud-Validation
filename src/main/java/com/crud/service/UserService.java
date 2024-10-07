package com.crud.service;

import java.util.List; 
import com.crud.dto.UserDto;
public interface UserService {
	
	UserDto saveUser(UserDto userDto);

	List<UserDto> getByName(String name);
	
	UserDto getUserById(Integer id);
	
	List<UserDto> getAllUsers();
	
	UserDto updateUserById(UserDto userDto, Integer id);
	
	void deleteUserById(Integer id);
	

}
