package com.musthavecaffeine.recipeapp.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.musthavecaffeine.recipeapp.api.v1.model.IngredientDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class IngredientServiceTest {

	private static final Long ID = 1L;
	private static String RIJST = "Rijst";
	private static String APPEL = "Appel";
	private static String PEER = "Peer";
	
	@Autowired
	private IngredientService ingredientService;

	
	@Test
	@DirtiesContext
	public void getAllIngredients() {
		log.info(">>>>>>>>>>>>>>> RUNNING TESTCASE IngredientServiceTest.getAllIngredients() <<<<<<<<<<<<<<");
		List<IngredientDto> ingredientDtos = ingredientService.getAllIngredients();
		assertTrue(ingredientDtos.size() > 0);
	}
	
	@Test
	@DirtiesContext
	public void getIngredientById() {
		log.info(">>>>>>>>>>>>>>> RUNNING TESTCASE IngredientServiceTest.getIngredientById() <<<<<<<<<<<<<<");
		IngredientDto ingredientDto = ingredientService.getIngredientById(ID);
		assertEquals(ID,  ingredientDto.getId());
	}
	
	@Test
	@DirtiesContext
	public void getIngredientByName() {
		log.info(">>>>>>>>>>>>>>> RUNNING TESTCASE IngredientServiceTest.getIngredientByName() <<<<<<<<<<<<<<");
		IngredientDto ingredientDto = ingredientService.getIngredientByName(RIJST);
		assertEquals(RIJST, ingredientDto.getName());
	}
	
	@Test
	@DirtiesContext
	public void createNewIngredient() {
		log.info(">>>>>>>>>>>>>>> RUNNING TESTCASE IngredientServiceTest.createNewIngredient() <<<<<<<<<<<<<<");
		IngredientDto ingredientDto = new IngredientDto();
		
		// intentionally set an existing id
		// we will test on this later to make sure it has changed (indicating a create instead of an update)
		ingredientDto.setId(ID); 
		ingredientDto.setName(APPEL);
		// amount will be ignored because it is not an attribute of the ingredient entity
		ingredientDto.setAmount(1.0F);
		
		IngredientDto ingredientDto2 = ingredientService.createNewIngredient(ingredientDto);
		
		assertNotEquals(ingredientDto.getId(), ingredientDto2.getId());
		assertEquals(ingredientDto.getName(), ingredientDto2.getName());
		assertNull(ingredientDto2.getAmount());
	}
	
	@Test
	@DirtiesContext
	public void updateIngredient() {
		log.info(">>>>>>>>>>>>>>> RUNNING TESTCASE IngredientServiceTest.updateIngredient() <<<<<<<<<<<<<<");
		IngredientDto ingredientDto = ingredientService.getIngredientById(ID);
		String oldName = ingredientDto.getName();
		
		ingredientDto.setName(PEER);
		
		IngredientDto ingredientDto2 = ingredientService.updateIngredient(ingredientDto);
		
		assertEquals(ingredientDto.getId(), ingredientDto2.getId());
		assertNotEquals(oldName, ingredientDto2.getName());
		assertNull(ingredientDto2.getAmount());
	}
	
	@Test
	@DirtiesContext
	public void deleteIngredientById() {
		log.info(">>>>>>>>>>>>>>> RUNNING TESTCASE IngredientServiceTest.deleteIngredientById() <<<<<<<<<<<<<<");
		ingredientService.deleteIngredientById(ID);
		IngredientDto ingredientDto = ingredientService.getIngredientById(ID);
		assertNull(ingredientDto);
	}
	
}
