package com.musthavecaffeine.recipeapp.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "RecipeUser")
@Table(name = "recipe_user")
public class RecipeUser {

	@EmbeddedId
	private RecipeUserId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recipe_id", insertable = false, updatable = false)
	private Recipe recipe;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User user;

	@Column(name = "rating")
	private Long rating = 0L;

	public RecipeUser() {
	}

	public RecipeUser(Recipe recipe, User user, Long rating) {

		id = new RecipeUserId(recipe.getId(), user.getId());

		this.recipe = recipe;
		this.user = user;
		this.rating = rating;
	}

    public RecipeUserId getId() {
		return id;
	}

	public void setId(RecipeUserId id) {
		this.id = id;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getRating() {
		return rating;
	}

	public void setRating(Long rating) {
		if (rating > 5L) rating = 5L;
		this.rating = rating;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        RecipeUser that = (RecipeUser) o;
        return Objects.equals(recipe, that.recipe) &&
               Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipe, user);
    }

	@Override
	public String toString() {
		return "RecipeUser [id=" + id + ", recipe=" + recipe + ", user=" + user + ", rating=" + rating
				+ "]";
	}

}
