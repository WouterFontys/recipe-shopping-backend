package com.musthavecaffeine.recipeapp.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.musthavecaffeine.recipeapp.api.v1.mapper.IngredientMapper;
import com.musthavecaffeine.recipeapp.api.v1.mapper.RecipeMapper;
import com.musthavecaffeine.recipeapp.api.v1.model.IngredientDTO;
import com.musthavecaffeine.recipeapp.api.v1.model.RecipeDTO;
import com.musthavecaffeine.recipeapp.bootstrap.SpringJpaBootstrap;
import com.musthavecaffeine.recipeapp.domain.Ingredient;
import com.musthavecaffeine.recipeapp.domain.Recipe;
import com.musthavecaffeine.recipeapp.repositories.IngredientRepository;
import com.musthavecaffeine.recipeapp.repositories.IngredientRowRepository;
import com.musthavecaffeine.recipeapp.repositories.RecipeDaoRepository;
import com.musthavecaffeine.recipeapp.repositories.RecipeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RecipeServiceImplIntegrationTest {

	@Autowired
	IngredientRepository ingredientRepository;

	@Autowired
	IngredientRowRepository ingredientRowRepository;

	@Autowired
	RecipeRepository recipeRepository;

//	@Autowired
//	RecipeDaoRepository recipeDaoRepository;

	RecipeService recipeService;

	@Before
	public void setUp() throws Exception {
		SpringJpaBootstrap bootstrap = new SpringJpaBootstrap(ingredientRepository, ingredientRowRepository, recipeRepository);
		bootstrap.run();
		
		//recipeService = new RecipeServiceImpl(IngredientMapper.INSTANCE, RecipeMapper.INSTANCE, recipeDaoRepository, recipeRepository);
	}

	@Test
	public void getIngredientByName() {
//		long id = getRecipeIdValue();

//		Recipe recipe = recipeRepository.getOne(id);
//		assertNotNull(recipe);
//		
//		RecipeDTO recipeDto = recipeService.getRecipeById(recipe.getId());
//		assertNotNull(recipeDto);
//		
//		assertEquals(recipe.getId(), recipeDto.getId());
//		assertEquals(recipe.getName(), recipeDto.getName());
//		
//		System.out.println(">>>>>>>>>>" + recipe.toString());
//		System.out.println(">>>>>>>>>>" + recipeDto.toString());

	}

	private Long getRecipeIdValue() {
		List<Recipe> recipes = recipeRepository.findAll();

		// return first id
		return recipes.get(0).getId();
	}
}
