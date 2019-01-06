package com.musthavecaffeine.recipeapp.services;

import java.util.List;

import com.musthavecaffeine.recipeapp.api.v1.model.ShoppingListDto;

public interface ShoppingListService {

	List<ShoppingListDto> getAllShoppingLists(Long userId);

	ShoppingListDto getShoppingListById(Long id, Long userId);

	ShoppingListDto createNewShoppingList(ShoppingListDto recipeDto, Long userId);

	ShoppingListDto updateShoppingList(ShoppingListDto recipeDto, Long userId);

	void deleteShoppingListById(Long id, Long userId);
}
