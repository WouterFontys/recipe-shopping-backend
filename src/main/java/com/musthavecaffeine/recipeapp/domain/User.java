package com.musthavecaffeine.recipeapp.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany(
			mappedBy = "user",
			cascade = CascadeType.ALL
			)
	private List<Recipe> recipes = new ArrayList<>();

	@OneToMany(
			mappedBy = "user",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<RecipeUser> recipeUsers = new ArrayList<>();

	@OneToMany(
			mappedBy = "user",
			cascade = CascadeType.ALL
			)
	private List<ShoppingList> shoppingLists = new ArrayList<>();

	public void addRecipe(Recipe recipe) {
		recipe.setUser(this);
		recipes.add(recipe);
	}

	public void removeRecipe(Recipe recipe) {
		recipes.remove(recipe);
		recipe.setUser(null);
	}

	public List<RecipeUser> getRecipeUsers() {
		return recipeUsers;
	}

	public void addRecipeUser(RecipeUser recipeUser) {
		recipeUsers.add(recipeUser);
	}

	public void removeRecipeUser(RecipeUser recipeUser) {
		recipeUsers.remove(recipeUser);
	}

	public void addShoppingList(ShoppingList shoppingList) {
		shoppingList.setUser(this);
		shoppingLists.add(shoppingList);
	}

	public void removeShoppingList(ShoppingList shoppingList) {
		shoppingLists.remove(shoppingList);
		shoppingList.setUser(null);
	}

}
