package com.musthavecaffeine.recipeapp.services;

import java.util.List;

import com.musthavecaffeine.recipeapp.api.v1.model.RecipeDto;


public interface RecipeService {

	List<RecipeDto> getAllRecipes(Long userId);
	
	RecipeDto getRecipeById(Long id, Long userId);
	
	RecipeDto createNewRecipe(RecipeDto recipeDto, Long userId);
	
	RecipeDto updateRecipe(RecipeDto recipeDto, Long userId);
	
	void deleteRecipeById(Long id, Long userId);

}
