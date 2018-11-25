package com.musthavecaffeine.recipeapp.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.musthavecaffeine.recipeapp.api.v1.model.RecipeDTO;
import com.musthavecaffeine.recipeapp.domain.Recipe;

@Mapper
public interface RecipeMapper {

	RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);
	
	RecipeDTO recipeToRecipeDto(Recipe recipe);
	
	Recipe recipeDtoToRecipe(RecipeDTO recipeDto);
}
