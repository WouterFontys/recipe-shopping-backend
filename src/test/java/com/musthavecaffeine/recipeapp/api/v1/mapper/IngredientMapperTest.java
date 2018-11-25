package com.musthavecaffeine.recipeapp.api.v1.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.musthavecaffeine.recipeapp.api.v1.model.IngredientDTO;
import com.musthavecaffeine.recipeapp.domain.Ingredient;

public class IngredientMapperTest {

	public static final long ID = 1l;
	public static final String NAME = "Rice";

	IngredientMapper ingredientMapper = IngredientMapper.INSTANCE;
	
	@Test
	public void ingredientDtoToIngredient() throws Exception {
		
		// given
		Ingredient ingredient = new Ingredient();
		ingredient.setId(ID);
		ingredient.setName(NAME);
		
		//when
		IngredientDTO ingredientDto = ingredientMapper.ingredientToIngredientDto(ingredient);
		
		//then
		assertEquals(Long.valueOf(ID), ingredientDto.getId());
		assertEquals(NAME, ingredientDto.getName());
	}
}
