package com.musthavecaffeine.recipeapp.services;

import java.util.List;

import com.musthavecaffeine.recipeapp.api.v1.model.IngredientDto;

public interface IngredientService {

	List<IngredientDto> getAllIngredients();
	
	IngredientDto getIngredientById(Long id);
	
	IngredientDto getIngredientByName(String name);
	
	IngredientDto createNewIngredient(IngredientDto ingredientDto);
	
	IngredientDto updateIngredient(IngredientDto ingredientDto);
	
	void deleteIngredientById(Long id);
	
}
