package com.musthavecaffeine.recipeapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.musthavecaffeine.recipeapp.api.v1.mapper.RecipeMapper;
import com.musthavecaffeine.recipeapp.api.v1.model.RecipeDto;
import com.musthavecaffeine.recipeapp.domain.Recipe;
import com.musthavecaffeine.recipeapp.domain.User;
import com.musthavecaffeine.recipeapp.repositories.RecipeRepository;
import com.musthavecaffeine.recipeapp.repositories.UserRepository;
import com.musthavecaffeine.recipeapp.services.exceptions.ResourceNotFoundException;
import com.musthavecaffeine.recipeapp.services.exceptions.UnauthorizedException;

@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	
	private final UserRepository userRepository;
	
	private final RecipeMapper recipeMapper;
	
	public RecipeServiceImpl(RecipeRepository recipeRepository, UserRepository userRepository, RecipeMapper recipeMapper) {
		this.recipeRepository = recipeRepository;
		this.userRepository = userRepository;
		this.recipeMapper = recipeMapper;
	}

	@Override
	public List<RecipeDto> getAllRecipes(Long userId) {
		ArrayList<RecipeDto> recipeDtos = new ArrayList<RecipeDto>();
		
		List<Recipe> recipes = recipeRepository.findAll();
		for (Recipe recipe : recipes) {
			// only expose recipes that the user is allowed to see
			// alternatively we could have created a proper query to only
			// get those recipes from the db
			if (!recipe.getIsPrivate() || recipe.getUser().getId() == userId) {
				recipeDtos.add(recipeMapper.recipeToRecipeDto(recipe));
			}
		}
		
		return recipeDtos;
	}

	@Override
	public RecipeDto getRecipeById(Long id, Long userId) {
		Recipe recipe = recipeRepository
				.findById(id)
				.orElseThrow(ResourceNotFoundException::new);

		if (recipe.getUser().getId() != userId) {
			throw new UnauthorizedException();
		}
		
		return recipeMapper.recipeToRecipeDto(recipe);
	}

	@Override
	public RecipeDto createNewRecipe(RecipeDto recipeDto, Long userId) {
		
		User user = userRepository
				.findById(userId)
				.orElseThrow(UnauthorizedException::new);
		
		Recipe recipe = recipeMapper.recipeDtoToRecipe(user, recipeDto);
		return recipeMapper.recipeToRecipeDto(recipeRepository.save(recipe));
	}

	@Override
	public RecipeDto updateRecipe(RecipeDto recipeDto, Long userId) {
		
		if (recipeDto.getUserId() != userId) {
			throw new UnauthorizedException();
		}
		
		Recipe recipe = recipeRepository
				.findById(recipeDto.getId())
				.orElseThrow(ResourceNotFoundException::new);
		
		recipeMapper.recipeDtoToRecipe(recipe.getUser(), recipeDto, recipe);
		
		recipeDto = recipeMapper.recipeToRecipeDto(recipeRepository.save(recipe));

		return recipeDto;
	}

	@Override
	public void deleteRecipeById(Long id, Long userId) {
		
		Recipe recipe = recipeRepository
				.findById(id)
				.orElseThrow(ResourceNotFoundException::new);
		
		if (recipe.getUser().getId() == userId) {
			recipeRepository.deleteById(id);
		} else {
			throw new UnauthorizedException();
		}
	}

}
