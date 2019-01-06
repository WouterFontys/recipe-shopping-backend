package com.musthavecaffeine.recipeapp.api.v1.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.musthavecaffeine.recipeapp.api.v1.model.IngredientDto;
import com.musthavecaffeine.recipeapp.api.v1.model.ShoppingListDto;
import com.musthavecaffeine.recipeapp.domain.Ingredient;
import com.musthavecaffeine.recipeapp.domain.ShoppingList;
import com.musthavecaffeine.recipeapp.domain.User;

@Component
public class ShoppingListMapper {

	private final IngredientMapper ingredientMapper;

	public ShoppingListMapper(IngredientMapper ingredientMapper) {
		this.ingredientMapper = ingredientMapper;
	}
	
	public ShoppingListDto shoppingListToShoppingListDto(ShoppingList shoppingList) {
		ShoppingListDto shoppingListDto = new ShoppingListDto();
		shoppingListDto.setId(shoppingList.getId());
		shoppingListDto.setName(shoppingList.getName());
		shoppingListDto.setUserId(shoppingList.getUser().getId());
		
		for (Ingredient ingredient : shoppingList.getIngredients()) {
			IngredientDto ingredientDto = ingredientMapper.ingredientToIngredientDto(ingredient);
			shoppingListDto.addIngredient(ingredientDto);
		}
		
		return shoppingListDto;
	}
	
	public ShoppingList shoppingListDtoToShoppingList(User user, ShoppingListDto shoppingListDto) {
		return shoppingListDtoToShoppingList(user, shoppingListDto, new ShoppingList());
	}
	
	public ShoppingList shoppingListDtoToShoppingList(User user, ShoppingListDto shoppingListDto, ShoppingList shoppingList) {
		
		shoppingList.setName(shoppingListDto.getName());
		shoppingList.setUser(user);
		
		// add new ingredients to shoppinglist
		// we add new ingredients first so that we need to use the
		// ingedientMapper only once for each ingredient
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		for (IngredientDto ingredientDto : shoppingListDto.getIngredients()) {
			Ingredient ingredient = ingredientMapper.ingredientDtoToIngredient(ingredientDto);
			ingredients.add(ingredient);
			
			boolean addRecipe = true;
			for (Ingredient ingredient2 : shoppingList.getIngredients()) {
				
				if (ingredient2.getId() == ingredient.getId()) {
					addRecipe = false;
					break;
				}
			}
			if (addRecipe) {
				//Float amount = ingredientDto.getAmount();
				//shoppingList.addIngredient(ingredient, amount);
				shoppingList.addIngredient(ingredient);
			}
		}

		// remove the removed ingredients from the recipe
		for (Ingredient ingredient : ingredients) {
			
			Ingredient testIngredient = null;
			for (Ingredient ingredient2 : shoppingList.getIngredients()) {
				
				testIngredient = ingredient2;
				if (testIngredient.getId() == ingredient.getId()) {
					testIngredient = null;
					break;
				}
			}
			if (testIngredient != null) {
				shoppingList.removeIngredient(testIngredient);
			}
		}
		
		
		return shoppingList;
	}
}
