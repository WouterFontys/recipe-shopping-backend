package com.musthavecaffeine.recipeapp.services;

import com.musthavecaffeine.recipeapp.domain.Ingredient;

public interface IngredientService {

	Iterable<Ingredient> listAllIngredients();
	
	Ingredient getIngredientById(Integer id);
	
	Ingredient getIngredientByName(String name);
	
	Ingredient saveIngredient(Ingredient ingredient);
	
	void deleteIngredient(Integer id);
}
