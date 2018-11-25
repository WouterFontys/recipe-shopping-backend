package com.musthavecaffeine.recipeapp.controllers;


/*
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.musthavecaffeine.recipeapp.controllers.v1.IngredientController;
import com.musthavecaffeine.recipeapp.domain.Ingredient;
import com.musthavecaffeine.recipeapp.services.IngredientService;

@RunWith(SpringRunner.class)
@WebMvcTest(IngredientController.class)
public class IngredientRestControllerIntegrationTest {

	@Autowired
    private MockMvc mvc;
 
    @MockBean
    private IngredientService service;
    
    @Test
    public void givenIngredients_whenGetIngredients_thenReturnJsonArray()
      throws Exception {
    	
		Ingredient ingredient = new Ingredient();
		ingredient.setName("ei");
		
		List<Ingredient> allIngredients = Arrays.asList(ingredient);
		
		// TODO: fix this
//    	given(service.listAllIngredients()).willReturn(allIngredients);
//    	
//    	mvc.perform(get("/api/ingredient")
//    		      .contentType(MediaType.APPLICATION_JSON))
//    		      .andExpect(status().isOk())
//    		      .andExpect(jsonPath("$", hasSize(1)))
//    		      .andExpect(jsonPath("$[0].name", is(ingredient.getName())));
    }
}
*/