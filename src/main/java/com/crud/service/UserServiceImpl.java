package com.crud.service;

import java.util.List; 
import org.springframework.stereotype.Service;

import com.crud.dto.UserDto;
import com.crud.entity.User;
import com.crud.exception.ResourseNotFoundException;
import com.crud.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepo;
	private final ObjectMapper objectMapper;
		

	
	
	@Override
	public UserDto saveUser(UserDto user) {
		 User convertValue = this.objectMapper.convertValue(user, User.class);
		   
		return this.objectMapper.convertValue(this.userRepo.save(convertValue),UserDto.class );
	}

	@Override
	public UserDto getUserById(Integer id) {

	   User user = userRepo.findById(id).orElseThrow(() -> new ResourseNotFoundException("User Nhi Mila Is Id Pe : " + id));
        return this.objectMapper.convertValue(user, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {
		 List<User> allUsers = userRepo.findAll();
			return allUsers.stream().map(x -> objectMapper.convertValue(x, UserDto.class)).toList();
	}

	@Override
	public UserDto updateUserById(UserDto userDto, Integer id) {

		User user2 = userRepo.findById(id)
				.orElseThrow((() -> new ResourseNotFoundException("User Nhi Mila Is Id Pe : " + id)));

		if (user2 != null) {
			user2.setName(userDto.getName());
			user2.setAddress(userDto.getAddress());
			user2.setEmail(userDto.getEmail());
			user2.setAge(userDto.getAge());
			User save = userRepo.save(user2);
			return this.objectMapper.convertValue(save, UserDto.class);
		}
		return null;
	}

	@Override
	public void deleteUserById(Integer id) {
		userRepo.findById(id).orElseThrow(
				() -> new ResourseNotFoundException("Is Id Par Koi User Nhi Hai : " + id));
		userRepo.deleteById(id);
	}

	@Override
	public List<UserDto> getByName(String name) {
		  List<User> byName = this.userRepo.findByName(name);
		return byName.stream().map(x -> this.objectMapper.convertValue(x, UserDto.class)).toList();
	}

}
