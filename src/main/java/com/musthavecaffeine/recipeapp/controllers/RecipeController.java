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

import com.musthavecaffeine.recipeapp.domain.Recipe;
import com.musthavecaffeine.recipeapp.services.RecipeService;
import com.musthavecaffeine.recipeapp.services.RecipeServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/recipe")
@Api(tags = {"RecipeController"})
public class RecipeController {

	private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);
	
	private RecipeService recipeService;

	@Autowired
	public void setRecipeService(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@ApiOperation(value = "View a list of available recipes", response = Iterable.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = 200, message = "Successfully retrieved list"),
//			@ApiResponse(code = 401, message = "Your are not authenticated"),
//			@ApiResponse(code = 403, message = "You are not authorized to view the resource "),
//			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
//	})
	@RequestMapping(value = "/", method= RequestMethod.GET, produces = "application/json")
    public Iterable<Recipe> list(Model model){
        Iterable<Recipe> recipeList = recipeService.listAllRecipes();
        return recipeList;
	}
	
    @ApiOperation(value = "Search a recipe with an ID",response = Recipe.class)
    @RequestMapping(value = "/{id}", method= RequestMethod.GET, produces = "application/json")
    public Recipe showRecipe(@PathVariable Integer id, Model model){
    	Recipe recipe = recipeService.getRecipeById(id);
        return recipe;
    }

    @ApiOperation(value = "Add a recipe")
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Recipe> saveRecipe(@RequestBody Recipe recipe){
        Recipe result = recipeService.saveRecipe(recipe);
        //return new ResponseEntity("Recipe saved successfully", HttpStatus.OK);
        
        URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(result.getId()).toUri();

		logger.info(result.toString());
		
		return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "Update a recipe")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Integer id, @RequestBody Recipe recipe){
        Recipe storedRecipe = recipeService.getRecipeById(id);
        storedRecipe.setDescription(recipe.getDescription());
        storedRecipe.setImageUrl(recipe.getImageUrl());
        storedRecipe.setPreperationTime(recipe.getPreperationTime());

        Recipe result = recipeService.saveRecipe(storedRecipe);
        return new ResponseEntity<Recipe>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a recipe")
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        recipeService.deleteRecipe(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
