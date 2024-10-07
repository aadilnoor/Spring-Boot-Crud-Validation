package com.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.entity.User;
import java.util.List;


public interface UserRepository  extends JpaRepository<User, Integer> {
	 List<User> findByName(String name);
}
