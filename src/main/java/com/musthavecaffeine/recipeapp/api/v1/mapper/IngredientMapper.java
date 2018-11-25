package com.musthavecaffeine.recipeapp.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.musthavecaffeine.recipeapp.api.v1.model.IngredientDTO;
import com.musthavecaffeine.recipeapp.domain.Ingredient;

@Mapper
public interface IngredientMapper {

	IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);
	
	IngredientDTO ingredientToIngredientDto(Ingredient ingredient);
	
	Ingredient ingredientDtoToIngredient(IngredientDTO ingredientDto);
}
