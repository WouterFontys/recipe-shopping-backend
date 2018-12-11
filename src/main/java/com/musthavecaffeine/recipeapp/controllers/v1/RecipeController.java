package com.musthavecaffeine.recipeapp.controllers.v1;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.musthavecaffeine.recipeapp.api.v1.model.RecipeDTO;
import com.musthavecaffeine.recipeapp.api.v1.model.RecipeListDTO;
import com.musthavecaffeine.recipeapp.domain.Recipe;
import com.musthavecaffeine.recipeapp.services.RecipeService;
import com.musthavecaffeine.recipeapp.services.RecipeServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = {"RecipeController"})
@CrossOrigin
@RestController
@RequestMapping(RecipeController.BASE_URL)
public class RecipeController {

	public static final String BASE_URL = "/api/v1/recipe";
	
	private RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@ApiOperation(value = "Get a list of available recipes", response = List.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = 200, message = "Successfully retrieved list"),
//			@ApiResponse(code = 401, message = "Your are not authenticated"),
//			@ApiResponse(code = 403, message = "You are not authorized to view the resource "),
//			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
//	})
//	@GetMapping
//	@ResponseStatus(HttpStatus.OK)
//    public RecipeListDTO list(Model model){
//        return new RecipeListDTO(recipeService.getAllRecipes());
//	}
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
    public ArrayList<RecipeDTO> list(Model model){
        return new ArrayList<RecipeDTO>(recipeService.getAllRecipes());
	}
	
	
	
    @ApiOperation(value = "Find a recipe by ID",response = Recipe.class)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RecipeDTO getRecipeById(@PathVariable Long id){
    	return recipeService.getRecipeById(id);
    }

    @ApiOperation(value = "Add a new recipe")
    @PostMapping
    public RecipeDTO createNewRecipe(@RequestBody RecipeDTO recipeDto){
        return recipeService.createNewRecipe(recipeDto);
        
//        URI location = ServletUriComponentsBuilder
//				.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(result.getId()).toUri();
//
//		log.info(result.toString());
//		
//		return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "Update a recipe by ID")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RecipeDTO updateRecipe(@PathVariable Long id, @RequestBody RecipeDTO recipeDto){
    	return recipeService.saveRecipeByDto(id, recipeDto);
    }

    @ApiOperation(value = "Delete a recipe by ID")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        recipeService.deleteRecipeById(id);
    }
}
