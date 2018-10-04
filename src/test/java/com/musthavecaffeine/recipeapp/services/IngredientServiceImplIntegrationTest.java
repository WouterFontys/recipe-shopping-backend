package com.musthavecaffeine.recipeapp.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.musthavecaffeine.recipeapp.domain.Ingredient;
import com.musthavecaffeine.recipeapp.repositories.IngredientRepository;

@RunWith(SpringRunner.class)
public class IngredientServiceImplIntegrationTest {

	@TestConfiguration
    static class IngredientServiceImplTestContextConfiguration {
  
        @Bean
        public IngredientService employeeService() {
            return new IngredientServiceImpl();
        }
    }
	
	@Autowired
	private IngredientService ingredientService;
	
	@MockBean
	private IngredientRepository ingredientRepository;
	
	@Before
	public void setUp() {
		Ingredient ingredient = new Ingredient();
		ingredient.setName("ei");
	 
	    Mockito.when(ingredientRepository.findByName(ingredient.getName()))
	      .thenReturn(ingredient);
	}
	
	@Test
	public void whenValidName_thenIngredientShouldBeFound() {
	    String name = "ei";
	    Ingredient found = ingredientService.getIngredientByName(name);
	  
	     assertThat(found.getName())
	      .isEqualTo(name);
	 }
}
