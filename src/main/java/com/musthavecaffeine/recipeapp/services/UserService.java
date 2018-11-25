package com.musthavecaffeine.recipeapp.services;

import java.util.List;

import com.musthavecaffeine.recipeapp.api.v1.model.UserDTO;


public interface UserService {

	List<UserDTO> getAllUsers();
	
	UserDTO getUserById(Long id);
	
	UserDTO createNewUser(UserDTO userDto);
	
	UserDTO saveUserByDto(Long id, UserDTO userDto);
	
	void deleteUserById(Long id);
}
