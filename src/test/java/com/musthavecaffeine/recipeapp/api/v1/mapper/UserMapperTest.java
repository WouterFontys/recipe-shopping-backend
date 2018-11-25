package com.musthavecaffeine.recipeapp.api.v1.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.musthavecaffeine.recipeapp.api.v1.model.IngredientDTO;
import com.musthavecaffeine.recipeapp.api.v1.model.UserDTO;
import com.musthavecaffeine.recipeapp.domain.Ingredient;
import com.musthavecaffeine.recipeapp.domain.User;

public class UserMapperTest {

	public static final long ID = 1l;
	public static final String NAME = "Joe";

	UserMapper userMapper = UserMapper.INSTANCE;
	
	@Test
	public void ingredientDtoToIngredient() throws Exception {
		
		// given
		User user = new User();
		user.setId(ID);
		user.setName(NAME);
		
		//when
		UserDTO userDto = userMapper.userToUserDto(user);
		
		//then
		assertEquals(Long.valueOf(ID), userDto.getId());
		assertEquals(NAME, userDto.getName());
	}
}
