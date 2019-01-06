package com.musthavecaffeine.recipeapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.musthavecaffeine.recipeapp.api.v1.mapper.IngredientMapper;
import com.musthavecaffeine.recipeapp.api.v1.model.IngredientDto;
import com.musthavecaffeine.recipeapp.domain.Ingredient;
import com.musthavecaffeine.recipeapp.repositories.IngredientRepository;

@Service
public class IngredientServiceImpl implements IngredientService {

	private final IngredientRepository ingredientRepository;
	
	private final IngredientMapper ingredientMapper;
	
	public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientMapper ingredientMapper) {
		this.ingredientRepository = ingredientRepository;
		this.ingredientMapper = ingredientMapper;
	}

	@Override
	public List<IngredientDto> getAllIngredients() {

		ArrayList<IngredientDto> ingredientDtos = new ArrayList<>();
		
		List<Ingredient> ingredients = ingredientRepository.findAll();
		for (Ingredient ingredient : ingredients) {
			ingredientDtos.add(ingredientMapper.ingredientToIngredientDto(ingredient));
		}
		
		return ingredientDtos;
	}

	@Override
	public IngredientDto getIngredientById(Long id) {
		
		Optional<Ingredient> ingredient = ingredientRepository.findById(id);
		IngredientDto ingredientDto = null;
		if (ingredient.isPresent()) {
			ingredientDto = ingredientMapper.ingredientToIngredientDto(ingredient.get());
		}
		
		return ingredientDto;
	}

	@Override
	public IngredientDto getIngredientByName(String name) {
		
		Optional<Ingredient> ingredient = ingredientRepository.findByName(name);
		IngredientDto ingredientDto = null;
		if (ingredient.isPresent()) {
			ingredientDto = ingredientMapper.ingredientToIngredientDto(ingredient.get());
		}
		
		return ingredientDto;

	}

	@Override
	public IngredientDto createNewIngredient(IngredientDto ingredientDto) {

		// we don't want to update an existing ingredient so we make sure the id is null
		ingredientDto.setId(null);
	
		// check if an ingredient with the same name already exists
		Optional<Ingredient> ingredient = ingredientRepository.findByName(ingredientDto.getName());
		
		if (!ingredient.isPresent()) {
			// ingredient does not exist yet in the database
			Ingredient i = ingredientMapper.ingredientDtoToIngredient(ingredientDto);
			
			// save the ingredient to the database and map it to a DTO now that we have the ingredient id
			ingredientDto = ingredientMapper.ingredientToIngredientDto(ingredientRepository.save(i));
		} else {
			// ingredient already exists in the database, so we simple return the existing ingredient
			ingredientDto = ingredientMapper.ingredientToIngredientDto(ingredient.get());
		}

		return ingredientDto;
	}

	@Override
	public IngredientDto updateIngredient(IngredientDto ingredientDto) {

		Long id = ingredientDto.getId();
		if (id != null) {
			Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientDto.getId());
			if (ingredient.isPresent()) {
				Ingredient i = ingredient.get();
				i.setName(ingredientDto.getName());
				ingredientDto = ingredientMapper.ingredientToIngredientDto(ingredientRepository.save(i));
			} else {
				ingredientDto = null;
			}
		} else {
			ingredientDto = null;
		}
		
		return ingredientDto;
	}

	@Override
	public void deleteIngredientById(Long id) {
		ingredientRepository.deleteById(id);
	}

}
