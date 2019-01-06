package com.musthavecaffeine.recipeapp.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.musthavecaffeine.recipeapp.domain.Ingredient;
import com.musthavecaffeine.recipeapp.domain.Recipe;
import com.musthavecaffeine.recipeapp.repositories.IngredientRepository;
import com.musthavecaffeine.recipeapp.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeRepositoryTest {

//	@Autowired
//	EntityManager em;
//	
//	@Test
//	public void findById_basic() {
//	}
//
//	@Test
//	@DirtiesContext
//	public void deleteById_basic() {
//	}
//
//	@Test
//	@DirtiesContext
//	public void save_basic() {
//	}
	
	
	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Test
	@Transactional
	public void findAll() {
		log.info(">>>>>>>>>>>>>>> RUNNING TESTCASE findAll() <<<<<<<<<<<<<<");
		
		List<Recipe> recipes = recipeRepository.findAll();
		
		for (Recipe recipe : recipes) {
			log.info("Recipe -> {}", recipe);
		}

		log.info("Number of Recipes: {}", recipes.size());
		assertTrue(recipes.size() > 0);
		
	}
	
	@Test
	@Transactional
	public void findById() {
		log.info(">>>>>>>>>>>>>>> RUNNING TESTCASE findById() <<<<<<<<<<<<<<");
		
		Long id = getFirstId();
		log.info("First id: {}", id);
		
		Optional<Recipe> optional = recipeRepository.findById(id);
		assertTrue(optional.isPresent());
		assertEquals(id, optional.get().getId());
		
	}
	
	@Test
	@Transactional
	public void save() {
		
		log.info(">>>>>>>>>>>>>>> RUNNING TESTCASE ingredientRepositoryTest.save() <<<<<<<<<<<<<<");
		
		Ingredient ui = ingredientRepository.findByName("Ui").get();
		Ingredient wortel = ingredientRepository.findByName("Wortel").get();
		Ingredient aardappel = ingredientRepository.findByName("Aardappel").get();
		
		
		/*
		 * Save 
		 */
		Recipe recipe = new Recipe();
		recipe.setName("Boerenkool");
		recipeRepository.save(recipe);
		
		recipe.addIngredient(ui, 2.0F);
		recipe.addIngredient(wortel, 6.0F);
		recipe.addIngredient(aardappel, 3.5F);
		
		recipeRepository.save(recipe);
		log.info("Recipe: {}", recipe);
		
		
		
//		Optional<Recipe> optionalRecipe = recipeRepository.findByName("Boerenkool");
//		assertTrue(optionalRecipe.isPresent());
//		Recipe recipeFromDb = optionalRecipe.get();
//		assertEquals(recipe.getId().longValue(), recipeFromDb.getId().longValue());

//		/*
//		 * Update 
//		 */
//		recipeFromDb.setName("Hutspot");
//		recipeRepository.save(recipeFromDb);
//		
//		
//		Optional<Recipe> optionalRecipe2 = recipeRepository.findByName("Boerenkool");
//		assertFalse(optionalRecipe2.isPresent());
//		
//		Optional<Recipe> optionalRecipe3 = recipeRepository.findByName("Hutspot");
//		assertTrue(optionalRecipe3.isPresent());
//		Recipe recipeFromDb2 = optionalRecipe3.get();
//		assertEquals(recipe.getId().longValue(), recipeFromDb2.getId().longValue());
//		log.info("ingredientFromDb2: {}", recipeFromDb2);
		
	}
	
	@Test
	@Transactional
	public void updateById() {
		log.info(">>>>>>>>>>>>>>> RUNNING TESTCASE updateById() <<<<<<<<<<<<<<");
		
		Long id = getFirstId();
		log.info("First id: {}", id);

		Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
		assertTrue(optionalRecipe.isPresent());
		Recipe recipe = optionalRecipe.get();
		assertEquals(id.longValue(), recipe.getId().longValue());
		
		
		Ingredient ui = ingredientRepository.findByName("Ui").get();
		Ingredient wortel = ingredientRepository.findByName("Wortel").get();
		Ingredient aardappel = ingredientRepository.findByName("Aardappel").get();

		recipe.addIngredient(ui, 2.0F);
		recipe.addIngredient(wortel, 6.0F);
		recipe.addIngredient(aardappel, 3.5F);
		
		Ingredient rijst = ingredientRepository.findByName("Rijst").get();		
		recipe.removeIngredient(rijst);
		
//		recipeRepository.save(recipe);
		log.info("Recipe: {}", recipe);
		
	}
	
	
	@Test
	@Transactional
	public void deleteById() {
		log.info(">>>>>>>>>>>>>>> RUNNING TESTCASE deleteById() <<<<<<<<<<<<<<");
		
		Long id = getFirstId();
		log.info("First id: {}", id);
		
		recipeRepository.deleteById(id);
		
	}
	
	private Long getFirstId() {
		List<Recipe> recipes = recipeRepository.findAll();
		return recipes.get(0).getId();
	}
}

