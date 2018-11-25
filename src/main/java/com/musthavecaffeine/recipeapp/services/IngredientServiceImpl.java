package com.musthavecaffeine.recipeapp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.musthavecaffeine.recipeapp.api.v1.mapper.IngredientMapper;
import com.musthavecaffeine.recipeapp.api.v1.model.IngredientDTO;
import com.musthavecaffeine.recipeapp.api.v1.model.IngredientListDTO;
import com.musthavecaffeine.recipeapp.domain.Ingredient;
import com.musthavecaffeine.recipeapp.repositories.IngredientRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

	private final IngredientMapper ingredientMapper;
	private final IngredientRepository ingredientRepository;
	
	
	public IngredientServiceImpl(IngredientMapper ingredientMapper, IngredientRepository ingredientRepository) {
		this.ingredientMapper = ingredientMapper;
		this.ingredientRepository = ingredientRepository;
	}

	@Override
	public IngredientListDTO getAllIngredients() {
		log.debug("getAllIngredients called");
		List<IngredientDTO> ingredientDtos = ingredientRepository
				.findAll()
                .stream()
                .map(ingredient -> {
                   IngredientDTO ingredientDTO = ingredientMapper.ingredientToIngredientDto(ingredient);
//                   ingredientDTO.setIngredientUrl(getIngredientUrl(ingredient.getId()));
                   return ingredientDTO;
                })
                .collect(Collectors.toList());

		return new IngredientListDTO(ingredientDtos);
	}

	@Override
	public IngredientDTO getIngredientById(Long id) {
		log.debug("getIngredientById called with id: {}", id);
		return ingredientRepository.findById(id)
				.map(ingredientMapper::ingredientToIngredientDto)
//				.map(ingredientDTO -> {
//					// set API URL
//					ingredientDTO.setIngredientUrl(getIngredientUrl(id));
//					return ingredientDTO;
//				})
				.orElseThrow(ResourceNotFoundException::new);
	}

	@Override
	public IngredientDTO getIngredientByName(String name) {
		log.debug("getIngredientByName called with id: {}", name);
		return ingredientMapper.ingredientToIngredientDto(ingredientRepository.findByName(name));
	}
	
	@Override
	public IngredientDTO createNewIngredient(IngredientDTO ingredientDto) {
		log.debug("createNewIngredient called: {}", ingredientDto.toString());
		return saveAndReturnDto(ingredientMapper.ingredientDtoToIngredient(ingredientDto));
	}
	
	@Override
	public IngredientDTO saveIngredientByDto(Long id, IngredientDTO ingredientDto) {
		log.debug("saveIngredientByDto called: {}", ingredientDto.toString());
		Ingredient ingredient = ingredientMapper.ingredientDtoToIngredient(ingredientDto);
		ingredient.setId(id);
		return saveAndReturnDto(ingredient);
	}	
	
	@Override
	public void deleteIngredientById(Long id) {
		log.debug("deleteIngredientById called with id: {}", id);
		ingredientRepository.deleteById(id);
	}
	
	private IngredientDTO saveAndReturnDto(Ingredient ingredient) {
		Ingredient savedIngredient = ingredientRepository.save(ingredient);
		IngredientDTO returnDto = ingredientMapper.ingredientToIngredientDto(savedIngredient);
		return returnDto;
	}

}
