package com.musthavecaffeine.recipeapp.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "Recipe")
@Table(name = "recipe")
public class Recipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	private String description;
	private String imageUrl;
	private Long preparationTime;

	private Boolean isPrivate = false;

	private Long numberOfOneStarRatings;
	private Long numberOfTwoStarRatings;
	private Long numberOfThreeStarRatings;
	private Long numberOfFourStarRatings;
	private Long numberOfFiveStarRatings;
	private Float ratingAverage;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	
	@OneToMany(
			mappedBy = "recipe",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<RecipeIngredient> ingredients = new ArrayList<>();

	@OneToMany(
			mappedBy = "recipe",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<RecipeUser> users = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Long getPreparationTime() {
		return preparationTime;
	}

	public void setPreparationTime(Long preparationTime) {
		this.preparationTime = preparationTime;
	}

	public Boolean getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public Long getNumberOfOneStarRatings() {
		return numberOfOneStarRatings;
	}

	public void setNumberOfOneStarRatings(Long numberOfOneStarRatings) {
		this.numberOfOneStarRatings = numberOfOneStarRatings;
	}

	public Long getNumberOfTwoStarRatings() {
		return numberOfTwoStarRatings;
	}

	public void setNumberOfTwoStarRatings(Long numberOfTwoStarRatings) {
		this.numberOfTwoStarRatings = numberOfTwoStarRatings;
	}

	public Long getNumberOfThreeStarRatings() {
		return numberOfThreeStarRatings;
	}

	public void setNumberOfThreeStarRatings(Long numberOfThreeStarRatings) {
		this.numberOfThreeStarRatings = numberOfThreeStarRatings;
	}

	public Long getNumberOfFourStarRatings() {
		return numberOfFourStarRatings;
	}

	public void setNumberOfFourStarRatings(Long numberOfFourStarRatings) {
		this.numberOfFourStarRatings = numberOfFourStarRatings;
	}

	public Long getNumberOfFiveStarRatings() {
		return numberOfFiveStarRatings;
	}

	public void setNumberOfFiveStarRatings(Long numberOfFiveStarRatings) {
		this.numberOfFiveStarRatings = numberOfFiveStarRatings;
	}

	public Float getRatingAverage() {
		return ratingAverage;
	}

	public void setRatingAverage(Float ratingAverage) {
		this.ratingAverage = ratingAverage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<RecipeIngredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<RecipeIngredient> ingredients) {
		this.ingredients = ingredients;
	}

	public void addIngredient(Ingredient ingredient, Float amount) {
		RecipeIngredient recipeIngredient = new RecipeIngredient(this, ingredient, amount);
		ingredients.add(recipeIngredient);
		ingredient.addRecipe(recipeIngredient);
	}

	public void removeIngredient(Ingredient ingredient) {
		for (Iterator<RecipeIngredient> iterator = ingredients.iterator();
				iterator.hasNext();) {
			RecipeIngredient recipeIngredient = iterator.next();

			if (recipeIngredient.getRecipe().equals(this) &&
					recipeIngredient.getIngredient().equals(ingredient)) {
				iterator.remove();
				recipeIngredient.getIngredient().removeRecipe(recipeIngredient);
				recipeIngredient.setRecipe(null);
				recipeIngredient.setIngredient(null);
			}
		}
	}

	public List<RecipeUser> getUsers() {
		return users;
	}

	public void setUsers(List<RecipeUser> users) {
		this.users = users;
	}

	public void addUserRating(User user, Long rating) {
		RecipeUser recipeUser = new RecipeUser(this, user, rating);
		users.add(recipeUser);
		user.addRecipeUser(recipeUser);
	}

	public void removeUserRating(User user) {
		for (Iterator<RecipeUser> iterator = users.iterator();
				iterator.hasNext();) {
			RecipeUser recipeUser = iterator.next();

			if (recipeUser.getRecipe().equals(this) &&
					recipeUser.getUser().equals(user)) {
				iterator.remove();
				recipeUser.getUser().removeRecipeUser(recipeUser);
				recipeUser.setRecipe(null);
				recipeUser.setUser(null);
			}
		}
	}

	public void updateRating(Long oldRating, Long newRating) {

		if (oldRating == newRating || oldRating > 5 || newRating > 5) {
			// nothing to update
			return;
		}

		if (numberOfOneStarRatings == null) {numberOfOneStarRatings = 0L;}
		if (numberOfTwoStarRatings == null) {numberOfTwoStarRatings = 0L;}
		if (numberOfThreeStarRatings == null) {numberOfThreeStarRatings = 0L;}
		if (numberOfFourStarRatings == null) {numberOfFourStarRatings = 0L;}
		if (numberOfFiveStarRatings == null) {numberOfFiveStarRatings = 0L;}

		switch(oldRating.intValue()) {
		case 1:
			numberOfOneStarRatings--;
			break;
		case 2:
			numberOfTwoStarRatings--;
			break;
		case 3:
			numberOfThreeStarRatings--;
			break;
		case 4:
			numberOfFourStarRatings--;
			break;
		case 5:
			numberOfFiveStarRatings--;
			break;
		default:
				// do nothing
		}

		switch(newRating.intValue()) {
		case 1:
			numberOfOneStarRatings++;
			break;
		case 2:
			numberOfTwoStarRatings++;
			break;
		case 3:
			numberOfThreeStarRatings++;
			break;
		case 4:
			numberOfFourStarRatings++;
			break;
		case 5:
			numberOfFiveStarRatings++;
			break;
		default:
				// do nothing
		}

		Long totalRatings = numberOfOneStarRatings + numberOfTwoStarRatings
							+ numberOfThreeStarRatings + numberOfFourStarRatings
							+ numberOfFiveStarRatings;

		ratingAverage = (numberOfOneStarRatings.floatValue() + numberOfTwoStarRatings * 2
						+ numberOfThreeStarRatings * 3 + numberOfFourStarRatings * 4
						+ numberOfFiveStarRatings * 5) / totalRatings;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Recipe recipe = (Recipe)o;
		return Objects.equals(name, recipe.name);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", name=" + name + ", description=" + description + ", imageUrl=" + imageUrl
				+ ", preparationTime=" + preparationTime + ", isPrivate=" + isPrivate
				+ ", numberOfOneStarRatings=" + numberOfOneStarRatings + ", numberOfTwoStarRatings="
				+ numberOfTwoStarRatings + ", numberOfThreeStarRatings=" + numberOfThreeStarRatings
				+ ", numberOfFourStarRatings=" + numberOfFourStarRatings + ", numberOfFiveStarRatings="
				+ numberOfFiveStarRatings + ", ratingAverage=" + ratingAverage + /*", recipesIngredients="
				+ recipesIngredients +*/ "]";
	}
	
}
