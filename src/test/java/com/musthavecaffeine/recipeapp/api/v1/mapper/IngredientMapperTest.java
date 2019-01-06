package com.musthavecaffeine.recipeapp.api.v1.mapper;

import static org.junit.Assert.*;

import org.junit.Test;

import com.musthavecaffeine.recipeapp.api.v1.model.IngredientDto;
import com.musthavecaffeine.recipeapp.domain.Ingredient;

public class IngredientMapperTest {

	private static final Long ID = 1L;
	private static String RIJST = "Rijst";
	private static String APPEL = "Appel";
	
	private IngredientMapper ingredientMapper = new IngredientMapper();
	
	@Test
	public void ingredientToIngredientDto_Id_is_null() {

		Ingredient ingredient = new Ingredient(RIJST);
		IngredientDto ingredientDto = ingredientMapper.ingredientToIngredientDto(ingredient);
		assertEquals(ingredient.getId(), ingredientDto.getId());
		assertEquals(ingredient.getName(), ingredientDto.getName());
	}

	@Test
	public void ingredientToIngredientDto_Id_is_not_null() {

		Ingredient ingredient = new Ingredient(APPEL);
		ingredient.setId(ID);
		IngredientDto ingredientDto = ingredientMapper.ingredientToIngredientDto(ingredient);
		assertEquals(ingredient.getId(), ingredientDto.getId());
		assertEquals(ingredient.getName(), ingredientDto.getName());
	}

	@Test
	public void ingredientDtoToIngredient_Id_is_null() {

		IngredientDto ingredientDto = new IngredientDto();
		ingredientDto.setName(RIJST);
		Ingredient ingredient = ingredientMapper.ingredientDtoToIngredient(ingredientDto);
		assertEquals(ingredientDto.getId(), ingredient.getId());
		assertEquals(ingredientDto.getName(), ingredient.getName());
	}

	@Test
	public void ingredientDtoToIngredient_Id_is_not_null() {

		IngredientDto ingredientDto = new IngredientDto();
		ingredientDto.setId(ID);
		ingredientDto.setName(APPEL);
		Ingredient ingredient = ingredientMapper.ingredientDtoToIngredient(ingredientDto);
		assertEquals(ingredientDto.getId(), ingredient.getId());
		assertEquals(ingredientDto.getName(), ingredient.getName());
		
	}
}
