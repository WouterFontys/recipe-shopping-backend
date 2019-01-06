package com.musthavecaffeine.recipeapp.api.v1.mapper;

import org.springframework.stereotype.Component;

import com.musthavecaffeine.recipeapp.api.v1.model.UserDto;
import com.musthavecaffeine.recipeapp.domain.User;

@Component
public class UserMapper {

	public User userDtoToUser(UserDto userDto) {
	
		User user = new User();
		user.setId(userDto.getId());
		user.setName(user.getName());
		
		return user;
	}
	
	public User userDtoToUser(UserDto userDto, User user) {
		
		user.setId(userDto.getId());
		user.setName(user.getName());
		
		return user;
	}
	
	public UserDto userToUserDto(User user) {
		
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		
		return userDto;		
	}
	
}
