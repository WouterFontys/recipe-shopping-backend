package com.musthavecaffeine.recipeapp.services;

import com.musthavecaffeine.recipeapp.domain.Recipe;

public interface RecipeService {

	Iterable<Recipe> listAllRecipes();
	
	Recipe getRecipeById(Integer id);
	
	Recipe saveRecipe(Recipe recipe);
	
	void deleteRecipe(Integer id);
}
