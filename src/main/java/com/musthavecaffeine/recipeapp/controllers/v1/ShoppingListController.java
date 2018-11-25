package com.musthavecaffeine.recipeapp.controllers.v1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.musthavecaffeine.recipeapp.api.v1.model.ShoppingListDTO;
import com.musthavecaffeine.recipeapp.api.v1.model.ShoppingListListDTO;
import com.musthavecaffeine.recipeapp.domain.ShoppingList;
import com.musthavecaffeine.recipeapp.services.ShoppingListService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = {"ShoppingListController"})
@RestController
@RequestMapping(ShoppingListController.BASE_URL)
public class ShoppingListController {

	public static final String BASE_URL = "/api/v1/shoppinglist";
	
	private final ShoppingListService shoppingListService;

	public ShoppingListController(ShoppingListService shoppingListService) {
		this.shoppingListService = shoppingListService;
	}
	
	@ApiOperation(value = "Get a list of available shoppinglists", response = List.class)
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
    public ShoppingListListDTO getListOfShoppingLists(){
        return new ShoppingListListDTO(shoppingListService.getAllShoppingLists());
	}
	
    @ApiOperation(value = "Find an shoppinglist by ID",response = ShoppingList.class)
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ShoppingListDTO getShoppingListById(@PathVariable Long id){
    	return shoppingListService.getShoppingListById(id);
    }

    @ApiOperation(value = "Add a new shoppinglist")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingListDTO createNewShoppingList(@RequestBody ShoppingListDTO shoppingListDto){
        return shoppingListService.createNewShoppingList(shoppingListDto);
        
//        URI location = ServletUriComponentsBuilder
//				.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(result.getId()).toUri();
//
//		logger.info(result.toString());
//		
//		return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "Update an shoppinglist")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ShoppingListDTO updateShoppingList(@PathVariable Long id, @RequestBody ShoppingListDTO shoppingListDto){
        return shoppingListService.saveShoppingListByDto(id, shoppingListDto);
    }

    @ApiOperation(value = "Delete an shoppinglist")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        shoppingListService.deleteShoppingListById(id);
    }
}
