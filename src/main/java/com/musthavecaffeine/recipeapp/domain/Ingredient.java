package com.musthavecaffeine.recipeapp.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "Ingredient")
@Table(name = "ingredient")
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@NaturalId
	private String name;
	
	@OneToMany(
			mappedBy = "ingredient",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<RecipeIngredient> recipes = new ArrayList<>();

	@ManyToMany(mappedBy = "ingredients")
    private List<ShoppingList> shoppingLists = new ArrayList<>();
	
	public Ingredient() {
	}

	
	public Ingredient(String name) {
		this.name = name;
	}

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

	public List<RecipeIngredient> getRecipes() {
		return recipes;
	}
	
	public void addRecipe(RecipeIngredient recipeIngredient) {
		recipes.add(recipeIngredient);
	}
	
	public void removeRecipe(RecipeIngredient recipeIngredient) {
		recipes.remove(recipeIngredient);
	}
	
	public void addShoppingList(ShoppingList shoppingList) {
		shoppingLists.add(shoppingList);
	}
	
	public void removeShoppingList(ShoppingList shoppingList) {
		shoppingLists.remove(shoppingList);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Ingredient ingredient = (Ingredient)o;
		return Objects.equals(name, ingredient.name);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public String toString() {
		return "Ingredient [ingredientId=" + id + ", name=" + name + /*", recipes=" + recipesIngredients +*/ "]";
	}
	
}
