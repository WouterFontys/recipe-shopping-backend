package com.musthavecaffeine.recipeapp.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.musthavecaffeine.recipeapp.domain.Ingredient;

@RunWith(SpringRunner.class)
@DataJpaTest
public class IngredientRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Test
	public void whenFindByName_thenReturnIngredient() {
		// given
		Ingredient ingredient = new Ingredient();
		ingredient.setName("ei");
		
		entityManager.persist(ingredient);
		entityManager.flush();
		
		// when
		Ingredient found = ingredientRepository.findByName(ingredient.getName());
		
		// then
		assertThat(found.getName())
	      .isEqualTo(ingredient.getName());
	}
}
