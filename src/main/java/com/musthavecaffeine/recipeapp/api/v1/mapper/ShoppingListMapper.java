package com.musthavecaffeine.recipeapp.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.musthavecaffeine.recipeapp.api.v1.model.ShoppingListDTO;
import com.musthavecaffeine.recipeapp.domain.ShoppingList;

@Mapper
public interface ShoppingListMapper {

	ShoppingListMapper INSTANCE = Mappers.getMapper(ShoppingListMapper.class);
	
	ShoppingListDTO shoppingListToShoppingListDto(ShoppingList shoppingList);
	
	ShoppingList shoppingListDtoToShoppingList(ShoppingListDTO shoppingListDto);
}
