package com.musthavecaffeine.recipeapp.api.v1.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.musthavecaffeine.recipeapp.api.v1.model.IngredientDto;
import com.musthavecaffeine.recipeapp.api.v1.model.RecipeDto;
import com.musthavecaffeine.recipeapp.domain.Ingredient;
import com.musthavecaffeine.recipeapp.domain.Recipe;
import com.musthavecaffeine.recipeapp.domain.RecipeIngredient;
import com.musthavecaffeine.recipeapp.domain.User;
import com.musthavecaffeine.recipeapp.repositories.IngredientRepositoryTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RecipeMapperTest {

	private static final Long RECIPE_ID = 1L;

	private static final String[] INGREDIENTS1 = {"Rijst", "Appel", "Peer"};
	private static final Long[] INGREDIENT_IDS1 = {45L, 59L, 83L};
	private static final Float[] INGREDIENT_AMOUNTS1 = {1.0F, 2.1F, 3.2F};
	
	private static final String[] INGREDIENTS2 = {"Appel", "Peer", "Suiker"};
	private static final Long[] INGREDIENT_IDS2 = {59L, 83L, 1234L};
	private static final Float[] INGREDIENT_AMOUNTS2 = {2.1F, 3.2F, 4.3F};
	
	private static final String UPDATED_NAME = "updated name";
	private static final String UPDATED_DESCRIPTION = "updated description";

	private RecipeMapper recipeMapper;
	private User user;
	
	@Before
	public void createRecipeMapper() {
		if (recipeMapper == null) {
			recipeMapper = new RecipeMapper(new IngredientMapper());
		}
		
		if (user == null) {
			user = new User();
			user.setId(1L);
			user.setName("User Name");
		}
	}
	
	@Test
	public void recipeToRecipeDto() {
		Recipe recipe = createRecipe();
		
		RecipeDto recipeDto = recipeMapper.recipeToRecipeDto(recipe);
		
		assertEquals(recipe.getDescription(), recipeDto.getDescription());
		assertEquals(recipe.getId(), recipeDto.getId());
		assertEquals(recipe.getImageUrl(), recipeDto.getImageUrl());
		assertEquals(recipe.getIsPrivate(), recipeDto.getIsPrivate());
		assertEquals(recipe.getName(), recipeDto.getName());
		assertEquals(recipe.getNumberOfFiveStarRatings(), recipeDto.getNumberOfFiveStarRatings());
		assertEquals(recipe.getNumberOfFourStarRatings(), recipeDto.getNumberOfFourStarRatings());
		assertEquals(recipe.getNumberOfThreeStarRatings(), recipeDto.getNumberOfThreeStarRatings());
		assertEquals(recipe.getNumberOfTwoStarRatings(), recipeDto.getNumberOfTwoStarRatings());
		assertEquals(recipe.getNumberOfOneStarRatings(), recipeDto.getNumberOfOneStarRatings());
		assertEquals(recipe.getPreparationTime(), recipeDto.getPreparationTime());
		assertEquals(recipe.getRatingAverage(), recipeDto.getRatingAverage());
		assertEquals(recipe.getUser().getId(), recipeDto.getUserId());

		boolean allIngredientsFound = true;
		ArrayList<RecipeIngredient> recipeIngredients = new ArrayList<RecipeIngredient>(recipe.getIngredients()); 
		for (RecipeIngredient recipeIngredient : recipeIngredients) {
			Ingredient ingredient = recipeIngredient.getIngredient();
			Float amount = recipeIngredient.getAmount();
			
			boolean found = false;
			for (IngredientDto ingredientDto : recipeDto.getIngredients()) {
				if (ingredientDto.getId() == ingredient.getId() 
						&& ingredientDto.getAmount() == amount
						&& ingredientDto.getName() == ingredient.getName()) {
					found = true;
					break;
				}
			}
			
			allIngredientsFound &= found;
		}
		
		assertTrue(allIngredientsFound);
	}

	@Test
	public void recipeDtoToRecipe_New_recipe() {
		
		RecipeDto recipeDto = createRecipeDto(null);
		
		Recipe recipe = recipeMapper.recipeDtoToRecipe(user, recipeDto);
		
		assertEquals(recipeDto.getDescription(), recipe.getDescription());
		assertEquals(recipeDto.getId(), recipe.getId());
		assertEquals(recipeDto.getImageUrl(), recipe.getImageUrl());
		assertEquals(recipeDto.getIsPrivate(), recipe.getIsPrivate());
		assertEquals(recipeDto.getName(), recipe.getName());
		assertNull(recipe.getNumberOfFiveStarRatings());
		assertNull(recipe.getNumberOfFourStarRatings());
		assertNull(recipe.getNumberOfThreeStarRatings());
		assertNull(recipe.getNumberOfTwoStarRatings());
		assertNull(recipe.getNumberOfOneStarRatings());
		assertEquals(recipeDto.getPreparationTime(), recipe.getPreparationTime());
		assertNull(recipe.getRatingAverage());
		assertEquals(recipeDto.getUserId(), recipe.getUser().getId());
		
		boolean allIngredientsFound = true;
		ArrayList<RecipeIngredient> recipeIngredients = new ArrayList<RecipeIngredient>(recipe.getIngredients()); 
		for (IngredientDto ingredientDto : recipeDto.getIngredients()) {
			
			boolean found = false;
			for (RecipeIngredient recipeIngredient : recipeIngredients) {
				Ingredient ingredient = recipeIngredient.getIngredient();
				Float amount = recipeIngredient.getAmount();
				
				if (ingredientDto.getId() == ingredient.getId()
						&& ingredientDto.getAmount() == amount
						&& ingredientDto.getName() == ingredient.getName())
				{
					found = true;
					break;
				}
				
			}
			allIngredientsFound &= found;
		}
		
		assertTrue(allIngredientsFound);
	}

	@Test
	public void recipeDtoToRecipe_Existing_recipe() {
		
		
		Recipe recipe = createRecipe();
		
		RecipeDto recipeDto = createRecipeDto(RECIPE_ID);
		// update some parameters so that it differs from existing recipe
		recipeDto.setDescription(UPDATED_DESCRIPTION);
		recipeDto.setName(UPDATED_NAME);
		recipeDto.setIsPrivate(!recipeDto.getIsPrivate());
		
		// remove the first ingredient
//		for (IngredientDto ingredientDto : recipeDto.getIngredients()) {
//			if (ingredientDto.getId() == INGREDIENT_IDS1[0]) {
//				
//			}
//		}
		recipeDto.getIngredients().remove(INGREDIENTS1[0]);
		// add new ingredient
		IngredientDto ingredientDto = new IngredientDto();
		ingredientDto.setId(INGREDIENT_IDS2[2]);
		ingredientDto.setAmount(INGREDIENT_AMOUNTS2[2]);
		ingredientDto.setName(INGREDIENTS2[2]);
		recipeDto.addIngredient(ingredientDto);
		
		recipeMapper.recipeDtoToRecipe(user, recipeDto, recipe);
		
		assertEquals(recipeDto.getDescription(), recipe.getDescription());
		assertEquals(recipeDto.getId(), recipe.getId());
		assertEquals(recipeDto.getImageUrl(), recipe.getImageUrl());
		assertEquals(recipeDto.getIsPrivate(), recipe.getIsPrivate());
		assertEquals(recipeDto.getName(), recipe.getName());
		assertNull(recipeDto.getNumberOfFiveStarRatings());
		assertNull(recipeDto.getNumberOfFourStarRatings());
		assertNull(recipeDto.getNumberOfThreeStarRatings());
		assertNull(recipeDto.getNumberOfTwoStarRatings());
		assertNull(recipeDto.getNumberOfOneStarRatings());
		assertEquals(recipeDto.getPreparationTime(), recipe.getPreparationTime());
		assertNull(recipeDto.getRatingAverage());
		assertEquals(recipeDto.getUserId(), recipe.getUser().getId());

		// check all newly added ingredients can be found
		boolean allIngredientsFound = true;
		ArrayList<RecipeIngredient> recipeIngredients = new ArrayList<RecipeIngredient>(recipe.getIngredients()); 
		for (IngredientDto ingredientDto2 : recipeDto.getIngredients()) {
			
			boolean found = false;
			for (RecipeIngredient recipeIngredient : recipeIngredients) {
				Ingredient ingredient = recipeIngredient.getIngredient();
				Float amount = recipeIngredient.getAmount();
				
				if (ingredientDto2.getId() == ingredient.getId()
						&& ingredientDto2.getAmount() == amount
						&& ingredientDto2.getName() == ingredient.getName())
				{
					found = true;
					break;
				}
				
			}
			allIngredientsFound &= found;
		}		
		assertTrue(allIngredientsFound);
		
		// check if all removed ingredients can no longer be found
		allIngredientsFound = true;
		for (RecipeIngredient recipeIngredient : recipe.getIngredients()) {
			Ingredient ingredient = recipeIngredient.getIngredient();
			Float amount = recipeIngredient.getAmount();

			boolean found = false;
			for (IngredientDto ingredientDto2 : recipeDto.getIngredients()) {
				if (ingredientDto2.getId() == ingredient.getId()
						&& ingredientDto2.getAmount() == amount
						&& ingredientDto2.getName() == ingredient.getName())
				{
					found = true;
					break;
				}
			}
			allIngredientsFound &= found;
		}
		assertTrue(allIngredientsFound);
	}
	
	
	
	private Recipe createRecipe() {
		Recipe recipe = new Recipe();
		recipe.setId(RECIPE_ID);
		recipe.setDescription("recipe desciption");
		recipe.setImageUrl("http://url");
		recipe.setIsPrivate(true);
		recipe.setName("recipe name");
		recipe.setNumberOfFiveStarRatings(5L);
		recipe.setNumberOfFourStarRatings(4L);
		recipe.setNumberOfThreeStarRatings(3L);
		recipe.setNumberOfTwoStarRatings(2L);
		recipe.setNumberOfOneStarRatings(1L);
		recipe.setPreparationTime(20L);
		recipe.setRatingAverage((5*5+4*4+3*3+2*2+1*1)/(5+4+3+2+1.0F));
		recipe.setUser(user);
		
		for (int i = 0; i < INGREDIENTS1.length; i++) {
			Ingredient ingredient = new Ingredient();
			ingredient.setId(INGREDIENT_IDS1[i]);
			ingredient.setName(INGREDIENTS1[i]);
			
			recipe.addIngredient(ingredient, INGREDIENT_AMOUNTS1[i]);
		}
		
		return recipe;
	}
	
	private RecipeDto createRecipeDto(Long id) {
		RecipeDto recipeDto = new RecipeDto();
		
		recipeDto.setId(id);
		recipeDto.setDescription("recipe desciption");
		recipeDto.setImageUrl("http://url");
		recipeDto.setIsPrivate(true);
		recipeDto.setName("recipe name");
		recipeDto.setPreparationTime(20L);
		recipeDto.setUserId(user.getId());
		
		Long index = 1L;
		for (int i = 0; i < INGREDIENTS1.length; i++) {
			IngredientDto ingredientDto = new IngredientDto();
			ingredientDto.setId(INGREDIENT_IDS1[i]);
			ingredientDto.setName(INGREDIENTS1[i]);
			ingredientDto.setAmount(INGREDIENT_AMOUNTS1[i]);
			
			recipeDto.addIngredient(ingredientDto);
		}
		
		return recipeDto;
	}
}
