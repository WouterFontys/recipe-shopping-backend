package com.musthavecaffeine.recipeapp.services;

import java.util.List;

import com.musthavecaffeine.recipeapp.api.v1.model.IngredientDTO;
import com.musthavecaffeine.recipeapp.api.v1.model.IngredientListDTO;

public interface IngredientService {

//	IngredientListDTO getAllIngredients();
	List<IngredientDTO> getAllIngredients();
	
	IngredientDTO getIngredientById(Long id);
	
	IngredientDTO getIngredientByName(String name);
	
	IngredientDTO createNewIngredient(IngredientDTO ingredientDto);
	
	IngredientDTO saveIngredientByDto(Long id, IngredientDTO ingredientDto);
	
	void deleteIngredientById(Long id);
}
