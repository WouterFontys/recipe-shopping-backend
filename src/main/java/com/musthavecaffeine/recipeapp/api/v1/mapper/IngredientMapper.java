package com.musthavecaffeine.recipeapp.api.v1.mapper;

import org.springframework.stereotype.Component;

import com.musthavecaffeine.recipeapp.api.v1.model.IngredientDto;
import com.musthavecaffeine.recipeapp.domain.Ingredient;

@Component
public class IngredientMapper {

	public IngredientDto ingredientToIngredientDto(Ingredient ingredient) {
		
		IngredientDto ingredientDto = new IngredientDto();
		ingredientDto.setId(ingredient.getId());
		ingredientDto.setName(ingredient.getName());

		return ingredientDto;
	}
	
	public Ingredient ingredientDtoToIngredient(IngredientDto ingredientDto) {
		
		Ingredient ingredient = new Ingredient();
		ingredient.setId(ingredientDto.getId());
		ingredient.setName(ingredientDto.getName());
		
		return ingredient;
	}
}

