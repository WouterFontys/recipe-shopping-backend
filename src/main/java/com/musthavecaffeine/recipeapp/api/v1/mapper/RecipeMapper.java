package com.musthavecaffeine.recipeapp.api.v1.mapper;


import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.musthavecaffeine.recipeapp.api.v1.model.IngredientDto;
import com.musthavecaffeine.recipeapp.api.v1.model.RecipeDto;
import com.musthavecaffeine.recipeapp.domain.Ingredient;
import com.musthavecaffeine.recipeapp.domain.Recipe;
import com.musthavecaffeine.recipeapp.domain.RecipeIngredient;
import com.musthavecaffeine.recipeapp.domain.User;

@Component
public class RecipeMapper {

	private final IngredientMapper ingredientMapper;
	
	public RecipeMapper(IngredientMapper ingredientMapper) {
		this.ingredientMapper = ingredientMapper;
	}

	public RecipeDto recipeToRecipeDto(Recipe recipe) {
		RecipeDto recipeDto = new RecipeDto();
		
		recipeDto.setId(recipe.getId());
		recipeDto.setDescription(recipe.getDescription());
		recipeDto.setImageUrl(recipe.getImageUrl());
		recipeDto.setIsPrivate(recipe.getIsPrivate());
		recipeDto.setName(recipe.getName());
		recipeDto.setNumberOfOneStarRatings(recipe.getNumberOfOneStarRatings());
		recipeDto.setNumberOfTwoStarRatings(recipe.getNumberOfTwoStarRatings());
		recipeDto.setNumberOfThreeStarRatings(recipe.getNumberOfThreeStarRatings());
		recipeDto.setNumberOfFourStarRatings(recipe.getNumberOfFourStarRatings());
		recipeDto.setNumberOfFiveStarRatings(recipe.getNumberOfFiveStarRatings());
		recipeDto.setPreparationTime(recipe.getPreparationTime());
		recipeDto.setRatingAverage(recipe.getRatingAverage());
		recipeDto.setUserId(recipe.getUser().getId());
		
		for (RecipeIngredient recipeIngredient : recipe.getIngredients()) {
			IngredientDto ingredientDto = ingredientMapper.ingredientToIngredientDto(recipeIngredient.getIngredient());
			ingredientDto.setAmount(recipeIngredient.getAmount());
			recipeDto.addIngredient(ingredientDto);
		}
		
		return recipeDto;
	}

	public Recipe recipeDtoToRecipe( User user, RecipeDto recipeDto) {
		return recipeDtoToRecipe(user, recipeDto, new Recipe());
	}
	
	public Recipe recipeDtoToRecipe(User user, RecipeDto recipeDto, Recipe recipe) {
		recipe.setDescription(recipeDto.getDescription());
		recipe.setImageUrl(recipeDto.getImageUrl());
		recipe.setIsPrivate(recipeDto.getIsPrivate());
		recipe.setName(recipeDto.getName());
		// do not update ratings
//		recipe.setNumberOfOneStarRatings(recipeDto.getNumberOfOneStarRatings());
//		recipe.setNumberOfTwoStarRatings(recipeDto.getNumberOfTwoStarRatings());
//		recipe.setNumberOfThreeStarRatings(recipeDto.getNumberOfThreeStarRatings());
//		recipe.setNumberOfFourStarRatings(recipeDto.getNumberOfFourStarRatings());
//		recipe.setNumberOfFiveStarRatings(recipeDto.getNumberOfFiveStarRatings());
		recipe.setPreparationTime(recipeDto.getPreparationTime());
//		recipe.setRatingAverage(recipeDto.getRatingAverage());
		recipe.setUser(user);
		
		
		// add new ingredients to recipe
		// we add new ingredients first so that we need to use the
		// ingedientMapper only once for each ingredient
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		for (IngredientDto ingredientDto : recipeDto.getIngredients()) {
			Ingredient ingredient = ingredientMapper.ingredientDtoToIngredient(ingredientDto);
			ingredients.add(ingredient);
			
			boolean addRecipe = true;
			for (RecipeIngredient recipeIngredient : recipe.getIngredients()) {
				
				if (recipeIngredient.getIngredient().getId() == ingredient.getId()) {
					addRecipe = false;
					break;
				}
			}
			if (addRecipe) {
				Float amount = ingredientDto.getAmount();
				recipe.addIngredient(ingredient, amount);
			}
		}

		// remove the removed ingredients from the recipe
		for (Ingredient ingredient : ingredients) {
			
			Ingredient testIngredient = null;
			for (RecipeIngredient recipeIngredient : recipe.getIngredients()) {
				
				testIngredient = recipeIngredient.getIngredient();
				if (testIngredient.getId() == ingredient.getId()) {
					testIngredient = null;
					break;
				}
			}
			if (testIngredient != null) {
				recipe.removeIngredient(testIngredient);
			}
		}
		
		return recipe;
	}
}
