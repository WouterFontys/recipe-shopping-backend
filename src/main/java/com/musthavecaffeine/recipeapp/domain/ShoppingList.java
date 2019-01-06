package com.musthavecaffeine.recipeapp.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name = "ShoppingList")
@Table(name  = "shoppinglist")
public class ShoppingList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	// we don't want to delete any ingredients when we delete the shoppinglist,
	// hence we cannot use CascadeType.ALL
	@ManyToMany(cascade = {
	        CascadeType.PERSIST,
	        CascadeType.MERGE
	    })
    @JoinTable(name = "shoppinglist_rows",
        joinColumns = @JoinColumn(name = "shoppinglist_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
	private List<Ingredient> ingredients = new ArrayList<>();
	
	public void addIngredient(Ingredient ingredient) {
		ingredients.add(ingredient);
        ingredient.addShoppingList(this);
    }
 
    public void removeIngredient(Ingredient ingredient) {
    	ingredients.remove(ingredient);
        ingredient.removeShoppingList(this);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingList)) return false;
        return id != null && id.equals(((ShoppingList) o).id);
    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

}
