package com.musthavecaffeine.recipeapp.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musthavecaffeine.recipeapp.domain.Ingredient;
import com.musthavecaffeine.recipeapp.repositories.IngredientRepository;

@Service
public class IngredientServiceImpl implements IngredientService {

	private static final Logger logger = LoggerFactory.getLogger(IngredientServiceImpl.class);
	
	private IngredientRepository ingredientRepository;
	
	
	@Autowired
	public void setIngredientRepository(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	@Override
	public Iterable<Ingredient> listAllIngredients() {
		logger.debug("listAllIngredients called");
		return ingredientRepository.findAll();
	}

	@Override
	public Ingredient getIngredientById(Integer id) {
		logger.debug("getIngredientById called with id: {}", id);
		return ingredientRepository.findById(id).orElse(null);
	}

	@Override
	public Ingredient getIngredientByName(String name) {
		logger.debug("getIngredientById called with name: {}", name);
		return ingredientRepository.findByName(name);
	}
	
	@Override
	public Ingredient saveIngredient(Ingredient ingredient) {
		logger.debug("saveIngredient called");
		return ingredientRepository.save(ingredient);
	}

	@Override
	public void deleteIngredient(Integer id) {
		logger.debug("deleteIngredient called with id: {}", id);
		ingredientRepository.deleteById(id);
	}

}
