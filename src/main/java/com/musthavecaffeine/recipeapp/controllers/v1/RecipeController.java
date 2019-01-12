package com.musthavecaffeine.recipeapp.controllers.v1;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.musthavecaffeine.recipeapp.api.v1.model.RecipeDto;
import com.musthavecaffeine.recipeapp.services.RecipeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"RecipeController"})
@CrossOrigin
@RestController
@RequestMapping(RecipeController.BASE_URL)
public class RecipeController {

	public static final String BASE_URL = "/api/v1/recipe";
	
	// temporary workaround till we have Spring Security in place
	public static final Long userId = 1L;
	
	private RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@ApiOperation(value = "Get a list of available recipes", response = ArrayList.class)
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<RecipeDto> list(Model model){

		return new ArrayList<RecipeDto>(recipeService.getAllRecipes(userId));
	}

	@ApiOperation(value = "Find a recipe by ID", response = RecipeDto.class)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RecipeDto getRecipeById(@PathVariable Long id){
    	return recipeService.getRecipeById(id, userId);
    }
    
	@ApiOperation(value = "Add a new recipe", response = RecipeDto.class)
    @PostMapping
    public RecipeDto createNewRecipe(@RequestBody RecipeDto recipeDto){
        return recipeService.createNewRecipe(recipeDto, userId);
    }
    
	@ApiOperation(value = "Update a recipe by ID", response = RecipeDto.class)
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RecipeDto updateRecipe(@PathVariable Long id, @RequestBody RecipeDto recipeDto){
    	return recipeService.updateRecipe(recipeDto, userId);
    }

	@ApiOperation(value = "Delete a recipe by ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        recipeService.deleteRecipeById(id, userId);
    }

	// Rating
	@ApiOperation(value = "Update a recipe rating by ID", response = RecipeDto.class)
    @PutMapping("/{id}/rating/{rating}")
    @ResponseStatus(HttpStatus.OK)
    public RecipeDto updateRecipe(@PathVariable Long id, @PathVariable Long rating){
		return recipeService.updateRecipeRating(id, rating, userId);
    }

}
