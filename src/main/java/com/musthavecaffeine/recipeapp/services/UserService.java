package com.musthavecaffeine.recipeapp.services;

import java.util.List;

import com.musthavecaffeine.recipeapp.api.v1.model.UserDto;


public interface UserService {

	List<UserDto> getAllUsers();
	
	UserDto getUserById(Long id);
	
	UserDto createNewUser(UserDto userDto);
	
	UserDto updateUser(Long userId, UserDto userDto);
	
	void deleteUserById(Long id);
}
