package com.musthavecaffeine.recipeapp.api.v1.model;

import java.util.HashSet;
import java.util.Set;

import com.musthavecaffeine.recipeapp.domain.Ingredient;
import com.musthavecaffeine.recipeapp.domain.IngredientRow;
import com.musthavecaffeine.recipeapp.domain.RecipeDao;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO {

	@ApiModelProperty(notes = "The database generated recipe ID")
	private Long id;
	
	@ApiModelProperty(notes = "The recipe name", required = true)
	private String name;
	
	@ApiModelProperty(notes = "The recipe description", required = true)
	private String description;
	
	@ApiModelProperty(notes = "The image URL of the dish", required = true)
	private String imageUrl;
	
	@ApiModelProperty(notes = "The preparation time of the dish (in minutes)", required = true)
	private Long preparationTime;
	
	@ApiModelProperty(notes = "The recipe is only visible to the recipe author", required = true)
	private Boolean privateRecipe = false;
	
//	@JsonManagedReference
//	@JsonIgnore
	@ApiModelProperty(notes = "The list of ingredients")
	private Set<IngredientDTO> ingredients = new HashSet<IngredientDTO>();
	
	@ApiModelProperty(notes = "The number of 1 start ratings")
	private Long numberOfOneStarRatings;
	
	@ApiModelProperty(notes = "The number of 2 start ratings")
	private Long numberOfTwoStarRatings;
	
	@ApiModelProperty(notes = "The number of 3 start ratings")
	private Long numberOfThreeStarRatings;

	@ApiModelProperty(notes = "The number of 4 start ratings")
	private Long numberOfFourStarRatings;

	@ApiModelProperty(notes = "The number of 5 start ratings")
	private Long numberOfFiveStarRatings;

	public void addIngredient(IngredientDTO ingredientDto) {
		if (ingredientDto != null) {
			ingredients.add(ingredientDto);
		}
	}
	
	public float getRating() {
		long nrOfRatings = numberOfOneStarRatings
				+ numberOfTwoStarRatings
				+ numberOfThreeStarRatings
				+ numberOfFourStarRatings
				+ numberOfFiveStarRatings;
		
		float rating = 0.0f;
		if (nrOfRatings > 0) {
			rating = (numberOfOneStarRatings 
					+ numberOfTwoStarRatings * 2
					+ numberOfThreeStarRatings * 3
					+ numberOfFourStarRatings * 4
					+ numberOfFiveStarRatings * 5) / nrOfRatings;
		}
		return rating;
	}

}
