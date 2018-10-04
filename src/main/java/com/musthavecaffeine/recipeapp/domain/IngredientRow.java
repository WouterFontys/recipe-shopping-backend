package com.musthavecaffeine.recipeapp.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModelProperty;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "The database generated ingredient ID")
	private Integer id;
	
	@Version
	@ApiModelProperty(notes = "The auto-generated version of the ingredient")
	private Integer version;
	
	@ApiModelProperty(notes = "The ingredient", required = true)
	@OneToOne
	private Ingredient ingredient;
	
	@ApiModelProperty(notes = "The ingredient amount", required = true)
	private String amount;
	
	@ApiModelProperty(notes = "The ingredient amount unit", required = true)
	@Enumerated(value = EnumType.STRING)
	private IngredientUnitType unit;
	
	@JsonBackReference
	@ManyToOne
	private Recipe recipe;
	
	
	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public IngredientUnitType getUnit() {
		return unit;
	}

	public void setUnit(IngredientUnitType unit) {
		this.unit = unit;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	@Override
	public String toString( ) {
		return String.format(
                "IngredientRow[id=%d, version=%d, amount='%s', unit='%s']",
                id, version, amount, unit);
	}
}
