package com.musthavecaffeine.recipeapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.musthavecaffeine.recipeapp.api.v1.mapper.IngredientMapper;
import com.musthavecaffeine.recipeapp.api.v1.mapper.RecipeMapper;
import com.musthavecaffeine.recipeapp.api.v1.model.IngredientDTO;
import com.musthavecaffeine.recipeapp.api.v1.model.RecipeDTO;
import com.musthavecaffeine.recipeapp.domain.Ingredient;
import com.musthavecaffeine.recipeapp.domain.IngredientRow;
import com.musthavecaffeine.recipeapp.domain.Recipe;
import com.musthavecaffeine.recipeapp.domain.RecipeDao;
import com.musthavecaffeine.recipeapp.repositories.IngredientRepository;
import com.musthavecaffeine.recipeapp.repositories.IngredientRowRepository;
import com.musthavecaffeine.recipeapp.repositories.RecipeDaoRepository;
import com.musthavecaffeine.recipeapp.repositories.RecipeRepository;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	private final IngredientMapper ingredientMapper;

	private final IngredientRepository ingredientRepository;
	private final IngredientRowRepository ingredientRowRepository;
	private final RecipeMapper recipeMapper;
	private final RecipeDaoRepository recipeDaoRepository;
	private final RecipeRepository recipeRepository;

	public RecipeServiceImpl(IngredientMapper ingredientMapper, IngredientRepository ingredientRepository,
			IngredientRowRepository ingredientRowRepository, RecipeMapper recipeMapper,
			RecipeDaoRepository recipeDaoRepository, RecipeRepository recipeRepository) {
		this.ingredientMapper = ingredientMapper;

		this.ingredientRepository = ingredientRepository;
		this.ingredientRowRepository = ingredientRowRepository;
		this.recipeMapper = recipeMapper;
		this.recipeDaoRepository = recipeDaoRepository;
		this.recipeRepository = recipeRepository;
	}

//	@Override
//	public List<RecipeDTO> getAllRecipes() {
//		log.debug("getAllRecipes called");
//		return recipeRepository
//				.findAll()
//                .stream()
//                .map(recipe -> {
//                   RecipeDTO recipeDto = recipeMapper.recipeToRecipeDto(recipe);
////                   recipeDTO.setRecipeUrl(getRecipeUrl(recipe.getId()));
//                   return recipeDto;
//                })
//                .collect(Collectors.toList());
//	}

//	@Override
//	public RecipeDTO getRecipeById(Long id) {
//		log.debug("getIngredientById called with id: {}", id);
//		
//		System.out.println(">>>>>>>>>>> " + recipeRepository.findById(id)
//		.map(recipeMapper::recipeToRecipeDto)
//		.orElseThrow(ResourceNotFoundException::new).toString());
//		
//		return recipeRepository.findById(id)
//				.map(recipeMapper::recipeToRecipeDto)
//				.orElseThrow(ResourceNotFoundException::new);
//	}


	@Override
	public List<RecipeDTO> getAllRecipes() {
		log.debug("getAllRecipes called");

		List<RecipeDTO> recipeDtoList = new ArrayList<RecipeDTO>();
		List<RecipeDao> recipeDaoList = recipeDaoRepository.findAllPublicForUser();

		RecipeDTO recipeDto = null;
		for (RecipeDao recipeDao : recipeDaoList) {
			if (recipeDto == null || recipeDto.getId() != recipeDao.getId()) {
				log.debug("recipe: " + recipeDao.toString());
				recipeDto = new RecipeDTO();
				recipeDtoList.add(recipeDto);
				recipeDto.setId(recipeDao.getId());
				recipeDto.setName(recipeDao.getName());
				recipeDto.setDescription(recipeDao.getDescription());
				recipeDto.setImageUrl(recipeDao.getImageUrl());
				recipeDto.setPreparationTime(recipeDao.getPreparationTime());

				recipeDto.setNumberOfOneStarRatings(recipeDao.getNumberOfOneStarRatings());
				recipeDto.setNumberOfTwoStarRatings(recipeDao.getNumberOfTwoStarRatings());
				recipeDto.setNumberOfThreeStarRatings(recipeDao.getNumberOfThreeStarRatings());
				recipeDto.setNumberOfFourStarRatings(recipeDao.getNumberOfFourStarRatings());
				recipeDto.setNumberOfFiveStarRatings(recipeDao.getNumberOfFiveStarRatings());	
			} 

			IngredientDTO ingredientDto = new IngredientDTO();
			ingredientDto.setId(recipeDao.getIngredientId());
			ingredientDto.setName(recipeDao.getIngredientName());
			ingredientDto.setAmount(recipeDao.getIngredientAmount());

			recipeDto.addIngredient(ingredientDto);
		}

		return recipeDtoList;
	}


	@Override
	public RecipeDTO getRecipeById(Long id) {
		log.debug("getRecipeById called with id: {}", id);

		List<RecipeDao> recipeDaoList = recipeDaoRepository.findById(id);

		RecipeDTO recipeDto = null;
		for (RecipeDao recipeDao : recipeDaoList) {

			if (recipeDao == null) {
				log.error("Huh? recipeDao == null!");
				continue;
			}

			if (recipeDto == null) {
				log.info("recipeDao: " + recipeDao.toString());
				recipeDto = new RecipeDTO();
				recipeDto.setId(recipeDao.getId());
				recipeDto.setName(recipeDao.getName());
				recipeDto.setDescription(recipeDao.getDescription());
				recipeDto.setImageUrl(recipeDao.getImageUrl());
				recipeDto.setPreparationTime(recipeDao.getPreparationTime());

				recipeDto.setNumberOfOneStarRatings(recipeDao.getNumberOfOneStarRatings());
				recipeDto.setNumberOfTwoStarRatings(recipeDao.getNumberOfTwoStarRatings());
				recipeDto.setNumberOfThreeStarRatings(recipeDao.getNumberOfThreeStarRatings());
				recipeDto.setNumberOfFourStarRatings(recipeDao.getNumberOfFourStarRatings());
				recipeDto.setNumberOfFiveStarRatings(recipeDao.getNumberOfFiveStarRatings());	
			} 

			IngredientDTO ingredientDto = new IngredientDTO();
			ingredientDto.setId(recipeDao.getIngredientId());
			ingredientDto.setName(recipeDao.getIngredientName());
			ingredientDto.setAmount(recipeDao.getIngredientAmount());

			recipeDto.addIngredient(ingredientDto);
		}

		return recipeDto;
	}	

	@Override
	public RecipeDTO createNewRecipe(RecipeDTO recipeDto) {
		log.info("createNewRecipe called: {}", recipeDto.toString());

		Recipe recipe = recipeMapper.recipeDtoToRecipe(recipeDto);

		Recipe savedRecipe = recipeRepository.save(recipe);

		for (IngredientDTO ingredientDto : recipeDto.getIngredients()) {
			log.info("{}", ingredientDto.toString());

			Ingredient ingredient = ingredientRepository.findById(ingredientDto.getId())
					.orElseThrow(ResourceNotFoundException::new);

			IngredientRow ir = new IngredientRow();
			ir.setAmount(ingredientDto.getAmount());
			ir.setIngredient(ingredient);
			ir.setRecipe(savedRecipe);

			ingredientRowRepository.save(ir);
		}

		recipeDto.setId(savedRecipe.getId());
		return recipeDto;

//		return saveAndReturnDto(recipe);
	}

	@Override
	public RecipeDTO saveRecipeByDto(Long id, RecipeDTO recipeDto) {
		log.debug("saveRecipeByDto called: {}", recipeDto.toString());
		Recipe recipe = recipeMapper.recipeDtoToRecipe(recipeDto);
		recipe.setId(id);
		return saveAndReturnDto(recipe);
	}

	@Override
	public void deleteRecipeById(Long id) {
		log.debug("deleteRecipeById called with id: {}", id);
//		ingredientRowRepository.deleteByRecipeId(id);
//		recipeRepository.deleteById(id);
		recipeDaoRepository.deleteById(id);
	}

	private RecipeDTO saveAndReturnDto(Recipe recipe) {
		Recipe savedRecipe = recipeRepository.save(recipe);
		RecipeDTO returnDto = recipeMapper.recipeToRecipeDto(savedRecipe);
		return returnDto;
	}

}
