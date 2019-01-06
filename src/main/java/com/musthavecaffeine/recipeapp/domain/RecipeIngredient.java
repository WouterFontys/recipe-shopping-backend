package com.musthavecaffeine.recipeapp.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "RecipeIngredient")
@Table(name = "recipe_ingredient")
public class RecipeIngredient {
	
	@EmbeddedId
	private RecipeIngredientId id;
	
	@ManyToOne(fetch = FetchType.LAZY)
//	@MapsId("recipeId")
	@JoinColumn(name = "recipe_id", insertable = false, updatable = false)
	private Recipe recipe;
	
	@ManyToOne(fetch = FetchType.LAZY)
//	@MapsId("ingredientId")
	@JoinColumn(name = "ingredient_id", insertable = false, updatable = false)
	private Ingredient ingredient;
	
	@Column(name = "amount")
	private Float amount = 0.0F;

	public RecipeIngredient() {
	}

	public RecipeIngredient(Recipe recipe, Ingredient ingredient, Float amount) {
		
		id = new RecipeIngredientId(recipe.getId(), ingredient.getId());
		
		this.recipe = recipe;
		this.ingredient = ingredient;
		this.amount = amount;
	}

    public RecipeIngredientId getId() {
		return id;
	}

	public void setId(RecipeIngredientId id) {
		this.id = id;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        RecipeIngredient that = (RecipeIngredient) o;
        return Objects.equals(recipe, that.recipe) &&
               Objects.equals(ingredient, that.ingredient);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(recipe, ingredient);
    }

	@Override
	public String toString() {
		return "RecipeIngredient [id=" + id + ", recipe=" + recipe + ", ingredient=" + ingredient + ", amount=" + amount
				+ "]";
	}

}

