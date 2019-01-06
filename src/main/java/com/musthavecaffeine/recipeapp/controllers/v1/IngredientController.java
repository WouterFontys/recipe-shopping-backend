package com.musthavecaffeine.recipeapp.controllers.v1;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.musthavecaffeine.recipeapp.api.v1.model.IngredientDto;
import com.musthavecaffeine.recipeapp.services.IngredientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"IngredientController"})
@RestController
@RequestMapping(IngredientController.BASE_URL)
public class IngredientController {

	public static final String BASE_URL = "/api/v1/ingredient";
	
	private final IngredientService ingredientService;

	public IngredientController(IngredientService ingredientService) {
		this.ingredientService = ingredientService;
	}

	@ApiOperation(value = "Get a list of available ingredients", response = ArrayList.class)
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
    public ArrayList<IngredientDto> list(Model model){
		return new ArrayList<IngredientDto>(ingredientService.getAllIngredients());
	}
	
	@ApiOperation(value = "Find an ingredient by ID", response = IngredientDto.class)
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public IngredientDto getIngredientById(@PathVariable Long id){
    	return ingredientService.getIngredientById(id);
    }
    
	@ApiOperation(value = "Add a new ingredient", response = IngredientDto.class)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IngredientDto createNewIngredient(@RequestBody IngredientDto ingredientDto){
        return ingredientService.createNewIngredient(ingredientDto);
    }
    
	@ApiOperation(value = "Update an ingredient", response = IngredientDto.class)
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public IngredientDto updateIngredient(@PathVariable Long id, @RequestBody IngredientDto ingredientDto){
    	return ingredientService.updateIngredient(ingredientDto);
    }
    
	@ApiOperation(value = "Delete an ingredient")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        ingredientService.deleteIngredientById(id);
    }

}
