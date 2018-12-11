package com.musthavecaffeine.recipeapp.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class RecipeDao {

	private Long id;
	private String name;
	private String description;
	private String imageUrl;
	private Long preparationTime;
	private Boolean isPrivate;
	
	private Long numberOfOneStarRatings = 0L;
	private Long numberOfTwoStarRatings = 0L;
	private Long numberOfThreeStarRatings = 0L;
	private Long numberOfFourStarRatings = 0L;
	private Long numberOfFiveStarRatings = 0L;
	
	@Id
	private Long ingredientId;
	private String ingredientName;
	private String ingredientAmount;
	
}
