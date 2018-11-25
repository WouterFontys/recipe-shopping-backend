package com.musthavecaffeine.recipeapp.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.musthavecaffeine.recipeapp.api.v1.model.UserDTO;
import com.musthavecaffeine.recipeapp.domain.User;

@Mapper
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	UserDTO userToUserDto(User user);
	
	User userDtoToUser(UserDTO userDto);
}
