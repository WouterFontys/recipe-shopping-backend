package com.musthavecaffeine.recipeapp.api.v1.model;

import java.util.ArrayList;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RecipeDto {

	@ApiModelProperty(notes = "The recipe id", required = false)
	private Long id;
	
	@ApiModelProperty(notes = "The recipe name", required = true)
	private String name;
	
	@ApiModelProperty(notes = "The recipe desciption", required = true)
	private String description;
	
	@ApiModelProperty(notes = "The url to a picture of the recipe", required = true)
	private String imageUrl;
	
	@ApiModelProperty(notes = "The preperation time needed to cook/bake the recipe", required = true)
	private Long preparationTime;

	@ApiModelProperty(notes = "The user id of the owner of the recipe", required = true)
	private Long userId;
	
	@ApiModelProperty(notes = "Flag to mark the recipe visible to the owner only", required = true)
	private Boolean isPrivate = false;

	@ApiModelProperty(notes = "The number of one star ratings (ignored during create and update)", required = false)
	private Long numberOfOneStarRatings;
	
	@ApiModelProperty(notes = "The number of two star ratings (ignored during create and update)", required = false)
	private Long numberOfTwoStarRatings;
	
	@ApiModelProperty(notes = "The number of three star ratings (ignored during create and update)", required = false)
	private Long numberOfThreeStarRatings;
	
	@ApiModelProperty(notes = "The number of four star ratings (ignored during create and update)", required = false)
	private Long numberOfFourStarRatings;
	
	@ApiModelProperty(notes = "The number of five star ratings (ignored during create and update)", required = false)
	private Long numberOfFiveStarRatings;
	
	@ApiModelProperty(notes = "The rating average (ignored during create and update)", required = false)
	private Float ratingAverage;

	@ApiModelProperty(notes = "The ingredients used by the recipe", required = false)
	private ArrayList<IngredientDto> ingredients;

	public void addIngredient(IngredientDto ingredientDto) {
		if (ingredients == null) {
			ingredients = new ArrayList<IngredientDto>();
		}
		
		ingredients.add(ingredientDto);
	}
}
