package com.musthavecaffeine.recipeapp.controllers;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.musthavecaffeine.recipeapp.domain.Ingredient;
import com.musthavecaffeine.recipeapp.services.IngredientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/ingredient")
@Api(tags = {"IngredientController"})
public class IngredientController {

	private static final Logger logger = LoggerFactory.getLogger(IngredientController.class);
	
	private IngredientService ingredientService;

	@Autowired
	public void setIngredientService(IngredientService ingredientService) {
		this.ingredientService = ingredientService;
	}
	
	@ApiOperation(value = "View a list of available ingredients", response = Iterable.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = 200, message = "Successfully retrieved list"),
//			@ApiResponse(code = 401, message = "Your are not authenticated"),
//			@ApiResponse(code = 403, message = "You are not authorized to view the resource "),
//			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
//	})
	@RequestMapping(value = "/", method= RequestMethod.GET, produces = "application/json")
    public Iterable<Ingredient> list(Model model){
        Iterable<Ingredient> ingredientList = ingredientService.listAllIngredients();
        return ingredientList;
	}
	
    @ApiOperation(value = "Search an ingredient with an ID",response = Ingredient.class)
    @RequestMapping(value = "/{id}", method= RequestMethod.GET, produces = "application/json")
    public Ingredient showIngredient(@PathVariable Integer id, Model model){
       Ingredient ingredient = ingredientService.getIngredientById(id);
        return ingredient;
    }

    @ApiOperation(value = "Add an ingredient")
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Ingredient> saveIngredient(@RequestBody Ingredient ingredient){
        Ingredient result = ingredientService.saveIngredient(ingredient);
        //return new ResponseEntity("Ingredient saved successfully", HttpStatus.OK);
        
        URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(result.getId()).toUri();

		logger.info(result.toString());
		
		return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "Update an ingredient")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable Integer id, @RequestBody Ingredient ingredient){
        Ingredient storedIngredient = ingredientService.getIngredientById(id);
        storedIngredient.setName(ingredient.getName());

        Ingredient result = ingredientService.saveIngredient(storedIngredient);
        return new ResponseEntity<Ingredient>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete an ingredient")
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        ingredientService.deleteIngredient(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
