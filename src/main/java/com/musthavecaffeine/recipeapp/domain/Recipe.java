package com.musthavecaffeine.recipeapp.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import lombok.Data;

@Data
@Entity
public class Recipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Version
	private Integer version;
	
	private String name;
	private String description;
	private String imageUrl;
	private Long preparationTime;

	private Boolean privateRecipe = false;
	
	private Long numberOfOneStarRatings = 0L;
	private Long numberOfTwoStarRatings = 0L;
	private Long numberOfThreeStarRatings = 0L;
	private Long numberOfFourStarRatings = 0L;
	private Long numberOfFiveStarRatings = 0L;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "recipe")
	private Set<IngredientRow> ingredientsRows = new HashSet<IngredientRow>();
}
