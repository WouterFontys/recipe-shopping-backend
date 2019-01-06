package com.musthavecaffeine.recipeapp.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;



// https://vladmihalcea.com/the-best-way-to-map-a-many-to-many-association-with-extra-columns-when-using-jpa-and-hibernate/
// https://thoughts-on-java.org/many-relationships-additional-properties/

@Embeddable
public class RecipeIngredientId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "recipe_id")
	private Long recipeId;
	
	@Column(name = "ingredient_id")
	private Long ingredientId;

	public RecipeIngredientId() {

	}

	public RecipeIngredientId(Long recipeId, Long ingredientId) {

		this.recipeId = recipeId;
		this.ingredientId = ingredientId;
	}

	public Long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Long recipeId) {
		this.recipeId = recipeId;
	}

	public Long getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(Long ingredientId) {
		this.ingredientId = ingredientId;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        RecipeIngredientId that = (RecipeIngredientId) o;
        return Objects.equals(recipeId, that.recipeId) &&
               Objects.equals(ingredientId, that.ingredientId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(recipeId, ingredientId);
    }
}
