package com.musthavecaffeine.recipeapp.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.musthavecaffeine.recipeapp.domain.Ingredient;
import com.musthavecaffeine.recipeapp.domain.Recipe;
import com.musthavecaffeine.recipeapp.repositories.IngredientRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
//@SpringBootTest
@DataJpaTest
public class IngredientRepositoryTest {

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
	private TestEntityManager em;
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Test
	public void findAll() {
//		em.persist(new Ingredient("Rijst"));
//		em.persist(new Ingredient("Suiker"));
//		em.persist(new Ingredient("Peper"));
//		em.persist(new Ingredient("Zout"));
		
		List<Ingredient> ingredients = ingredientRepository.findAll();
		
		assertEquals(15, ingredients.size());
	}
	

	@Test
	public void findById() {
		
		log.info(">>>>>>>>>>>>>>> RUNNING TESTCASE ingredientRepositoryTest.findById() <<<<<<<<<<<<<<");
		
//		em.persist(new Ingredient("Rijst"));
//		em.persist(new Ingredient("Suiker"));
//		em.persist(new Ingredient("Peper"));
//		em.persist(new Ingredient("Zout"));
		
		Optional<Ingredient> optionalIngredient = ingredientRepository.findById(1L);
		
		assertTrue(optionalIngredient.isPresent());
		Ingredient ingredient = optionalIngredient.get();
		assertEquals(1L, ingredient.getId().longValue());
		assertEquals("Rijst", ingredient.getName());
		
	}

	@Test
	public void findByName() {
		
		log.info(">>>>>>>>>>>>>>> RUNNING TESTCASE ingredientRepositoryTest.findByName() <<<<<<<<<<<<<<");
		
		Optional<Ingredient> optionalIngredient = ingredientRepository.findByName("Rijst");
		
		assertTrue(optionalIngredient.isPresent());
		Ingredient ingredient = optionalIngredient.get();
		assertEquals(1L, ingredient.getId().longValue());
		assertEquals("Rijst", ingredient.getName());
		
	}

	@Test
	public void save() {
		
		log.info(">>>>>>>>>>>>>>> RUNNING TESTCASE ingredientRepositoryTest.save() <<<<<<<<<<<<<<");
		
		/*
		 * Save 
		 */
		Ingredient ingredient = new Ingredient("Komkommer");
		ingredientRepository.save(ingredient);
		log.info("Ingredient: {}", ingredient);
		
		Optional<Ingredient> optionalIngredient = ingredientRepository.findByName("Komkommer");
		assertTrue(optionalIngredient.isPresent());
		Ingredient ingredientFromDb = optionalIngredient.get();
		assertEquals(ingredient.getId().longValue(), ingredientFromDb.getId().longValue());

		/*
		 * Update 
		 */
		ingredientFromDb.setName("Courgette");
		ingredientRepository.save(ingredientFromDb);
		
		
		Optional<Ingredient> optionalIngredient2 = ingredientRepository.findByName("Komkommer");
		assertFalse(optionalIngredient2.isPresent());
		
		Optional<Ingredient> optionalIngredient3 = ingredientRepository.findByName("Courgette");
		assertTrue(optionalIngredient3.isPresent());
		Ingredient ingredientFromDb2 = optionalIngredient.get();
		assertEquals(ingredient.getId().longValue(), ingredientFromDb2.getId().longValue());
		log.info("ingredientFromDb2: {}", ingredientFromDb2);
		
	}

	
	
	@Test
	@Transactional
	public void deleteById() {
		log.info(">>>>>>>>>>>>>>> RUNNING TESTCASE ingredientRepository.deleteById() <<<<<<<<<<<<<<");
		
		Long id = getFirstId();
		log.info("First id: {}", id);
		
		ingredientRepository.deleteById(id);
		
		Optional<Ingredient> optionalIngredient = ingredientRepository.findById(1L);
		assertFalse(optionalIngredient.isPresent());

	}
	
	private Long getFirstId() {
		List<Ingredient> ingredients = ingredientRepository.findAll();
		return ingredients.get(0).getId();
	}
	
	
	
	
}

