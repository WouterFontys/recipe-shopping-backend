package com.musthavecaffeine.recipeapp.api.v1.model;

import java.util.ArrayList;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ShoppingListDto {

	@ApiModelProperty(notes = "The shoppinglist id", required = false)
	private Long id;
	
	@ApiModelProperty(notes = "The shoppinglist name", required = true)
	private String name;

	@ApiModelProperty(notes = "The user id of the owner of the shoppinglist", required = true)
	private Long userId;
	
	@ApiModelProperty(notes = "The ingredients", required = false)
	private ArrayList<IngredientDto> ingredients;

	public void addIngredient(IngredientDto ingredientDto) {
		if (ingredients == null) {
			ingredients = new ArrayList<IngredientDto>();
		}
		
		ingredients.add(ingredientDto);
	}
}
