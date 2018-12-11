package com.musthavecaffeine.recipeapp.controllers.v1;

import java.util.ArrayList;
import java.util.List;

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

import com.musthavecaffeine.recipeapp.api.v1.model.IngredientDTO;
import com.musthavecaffeine.recipeapp.api.v1.model.IngredientListDTO;
import com.musthavecaffeine.recipeapp.api.v1.model.RecipeDTO;
import com.musthavecaffeine.recipeapp.domain.Ingredient;
import com.musthavecaffeine.recipeapp.services.IngredientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = {"IngredientController"})
@RestController
@RequestMapping(IngredientController.BASE_URL)
public class IngredientController {

	public static final String BASE_URL = "/api/v1/ingredient";
	
	private final IngredientService ingredientService;

	public IngredientController(IngredientService ingredientService) {
		this.ingredientService = ingredientService;
	}
	
	@ApiOperation(value = "Get a list of available ingredients", response = List.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = 200, message = "Successfully retrieved list"),
//			@ApiResponse(code = 401, message = "Your are not authenticated"),
//			@ApiResponse(code = 403, message = "You are not authorized to view the resource "),
//			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
//	})
//	@GetMapping
//	@ResponseStatus(HttpStatus.OK)
//    public IngredientListDTO getListOfIngredients(){
//        return ingredientService.getAllIngredients();
//	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
    public ArrayList<IngredientDTO> list(Model model){
        return new ArrayList<IngredientDTO>(ingredientService.getAllIngredients());
	}
	
    @ApiOperation(value = "Find an ingredient by ID",response = Ingredient.class)
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public IngredientDTO getIngredientById(@PathVariable Long id){
    	return ingredientService.getIngredientById(id);
    }

    @ApiOperation(value = "Add a new ingredient")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IngredientDTO createNewIngredient(@RequestBody IngredientDTO ingredientDto){
        return ingredientService.createNewIngredient(ingredientDto);
        
//        URI location = ServletUriComponentsBuilder
//				.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(result.getId()).toUri();
//
//		logger.info(result.toString());
//		
//		return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "Update an ingredient")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public IngredientDTO updateIngredient(@PathVariable Long id, @RequestBody IngredientDTO ingredientDto){
        return ingredientService.saveIngredientByDto(id, ingredientDto);
    }

    @ApiOperation(value = "Delete an ingredient")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        ingredientService.deleteIngredientById(id);
    }
}
