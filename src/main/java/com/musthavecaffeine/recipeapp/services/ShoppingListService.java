package com.musthavecaffeine.recipeapp.services;

import java.util.List;

import com.musthavecaffeine.recipeapp.api.v1.model.ShoppingListDTO;

public interface ShoppingListService {

	List<ShoppingListDTO> getAllShoppingLists();
	
	ShoppingListDTO getShoppingListById(Long id);
	
	ShoppingListDTO createNewShoppingList(ShoppingListDTO shoppingListDto);
	
	ShoppingListDTO saveShoppingListByDto(Long id, ShoppingListDTO shoppingListDto);
	
	void deleteShoppingListById(Long id);
}
