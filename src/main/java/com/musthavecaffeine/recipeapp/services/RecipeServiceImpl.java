package com.musthavecaffeine.recipeapp.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musthavecaffeine.recipeapp.domain.Recipe;
import com.musthavecaffeine.recipeapp.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {

	private static final Logger logger = LoggerFactory.getLogger(RecipeServiceImpl.class);
	
	private RecipeRepository recipeRepository;
	
	
	@Autowired
	public void setRecipeRepository(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Iterable<Recipe> listAllRecipes() {
		logger.debug("listAllRecipes called");
		return recipeRepository.findAll();
	}

	@Override
	public Recipe getRecipeById(Integer id) {
		logger.debug("getRecipeById called with id: {}", id);
		return recipeRepository.findById(id).orElse(null);
	}

	@Override
	public Recipe saveRecipe(Recipe recipe) {
		logger.debug("saveRecipe called");
		return recipeRepository.save(recipe);
	}

	@Override
	public void deleteRecipe(Integer id) {
		logger.debug("deleteRecipe called with id: {}", id);
		recipeRepository.deleteById(id);
	}

}
