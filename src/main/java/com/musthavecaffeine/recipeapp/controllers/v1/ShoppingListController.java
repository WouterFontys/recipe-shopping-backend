package com.musthavecaffeine.recipeapp.controllers.v1;

import java.util.ArrayList;

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

import com.musthavecaffeine.recipeapp.api.v1.model.ShoppingListDto;
import com.musthavecaffeine.recipeapp.services.ShoppingListService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"ShoppingListController"})
@RestController
@RequestMapping(ShoppingListController.BASE_URL)
public class ShoppingListController {

	public static final String BASE_URL = "/api/v1/shoppinglist";

	// temporary workaround till we have Spring Security in place
	public static final Long userId = 1L;
	
	private final ShoppingListService shoppingListService;
	
	public ShoppingListController(ShoppingListService shoppingListService) {
		this.shoppingListService = shoppingListService;
	}
	
	@ApiOperation(value = "Get a list of available shoppinglists", response = ArrayList.class)
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
    public ArrayList<ShoppingListDto> getListOfShoppingLists(){
        return new ArrayList<ShoppingListDto>(shoppingListService.getAllShoppingLists(userId));
	}
	
    @ApiOperation(value = "Find a shoppinglist by ID", response = ShoppingListDto.class)
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ShoppingListDto getShoppingListById(@PathVariable Long id){
    	return shoppingListService.getShoppingListById(id, userId);
    }

    @ApiOperation(value = "Add a new shoppinglist", response = ShoppingListDto.class)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingListDto createNewShoppingList(@RequestBody ShoppingListDto shoppingListDto){
        return shoppingListService.createNewShoppingList(shoppingListDto, userId);
    }

    @ApiOperation(value = "Update a shoppinglist", response = ShoppingListDto.class)
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ShoppingListDto updateShoppingList(@PathVariable Long id, @RequestBody ShoppingListDto shoppingListDto){
        return shoppingListService.updateShoppingList(shoppingListDto, userId);
    }

    @ApiOperation(value = "Delete a shoppinglist")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        shoppingListService.deleteShoppingListById(id, userId);
    }
}
