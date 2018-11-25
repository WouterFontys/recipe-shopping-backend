package com.musthavecaffeine.recipeapp.services;

import java.util.List;

import com.musthavecaffeine.recipeapp.api.v1.model.RecipeDTO;

public interface RecipeService {

	List<RecipeDTO> getAllRecipes();
	
	RecipeDTO getRecipeById(Long id);
	
	RecipeDTO createNewRecipe(RecipeDTO recipeDto);
	
	RecipeDTO saveRecipeByDto(Long id, RecipeDTO recipeDto);
	
	void deleteRecipeById(Long id);
}
