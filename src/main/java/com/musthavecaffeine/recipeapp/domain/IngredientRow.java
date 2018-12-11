package com.musthavecaffeine.recipeapp.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude = {"recipe", "ingredient"})
@EqualsAndHashCode(exclude = {"recipe", "ingredient"})
@Entity
public class IngredientRow {

	public enum IngredientUnitType {
		PIECES,
		GRAM,
		KG,
		ML,
		LITER,
		SPOON,
		THEESPOON;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@ApiModelProperty(notes = "The ingredient amount", required = true)
	private String amount;
	
//	@ApiModelProperty(notes = "The ingredient amount unit", required = true)
//	@Enumerated(value = EnumType.STRING)
//	private IngredientUnitType unit;
	
//	@ApiModelProperty(notes = "The ingredient", required = true)
	@ManyToOne(fetch = FetchType.EAGER)
	private Ingredient ingredient;
	
	@ManyToOne
	private Recipe recipe;

//	@JsonIgnore
	public Ingredient getIngredient() {
		return ingredient;
	}

	@JsonIgnore
	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}
	
	@JsonIgnore
	public Recipe getRecipe() {
		return recipe;
	}

	@JsonIgnore
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

}
