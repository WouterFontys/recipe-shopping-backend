package com.musthavecaffeine.recipeapp.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;



// https://vladmihalcea.com/the-best-way-to-map-a-many-to-many-association-with-extra-columns-when-using-jpa-and-hibernate/
// https://thoughts-on-java.org/many-relationships-additional-properties/

@Embeddable
public class RecipeUserId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "recipe_id")
	private Long recipeId;

	@Column(name = "user_id")
	private Long userId;

	public RecipeUserId() {

	}

	public RecipeUserId(Long recipeId, Long ingredientId) {

		this.recipeId = recipeId;
		this.userId = ingredientId;
	}

	public Long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Long recipeId) {
		this.recipeId = recipeId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        RecipeUserId that = (RecipeUserId) o;
        return Objects.equals(recipeId, that.recipeId) &&
               Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, userId);
    }
}
