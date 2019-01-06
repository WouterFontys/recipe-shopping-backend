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

import com.musthavecaffeine.recipeapp.api.v1.model.UserDto;
import com.musthavecaffeine.recipeapp.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"UserController"})
@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {

	public static final String BASE_URL = "/api/v1/user";
	
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@ApiOperation(value = "Get a list of available users", response = ArrayList.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = 200, message = "Successfully retrieved list"),
//			@ApiResponse(code = 401, message = "Your are not authenticated"),
//			@ApiResponse(code = 403, message = "You are not authorized to view the resource "),
//			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
//	})
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
    public ArrayList<UserDto> list(Model model){
		// TODO: protect this so that only admins can get a full list of all users
        return new ArrayList<UserDto>(userService.getAllUsers());
	}
	
    @ApiOperation(value = "Find a user by ID", response = UserDto.class)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable Long id){
    	// TODO: protect this so that the user can only get him/herself
    	return userService.getUserById(id);
    }

    @ApiOperation(value = "Add a new user", response = UserDto.class)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createNewUser(@RequestBody UserDto userDto){
    	// this API endpoint needs to be publicly available without the need of
    	// being logged in, otherwise a new user won't be able to register
        return userService.createNewUser(userDto);
    }

    @ApiOperation(value = "Update a user by ID", response = UserDto.class)
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto){

    	// TODO: protect this so that the user can only change him/herself
    	return userService.updateUser(id, userDto);
    }

    @ApiOperation(value = "Delete a user by ID", response = UserDto.class)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
    	//TODO: protect this so that the user can only delete him/herself
        userService.deleteUserById(id);
    }
}
