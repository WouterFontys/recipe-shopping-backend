package com.musthavecaffeine.recipeapp.controllers.v1;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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

import com.musthavecaffeine.recipeapp.api.v1.model.UserDTO;
import com.musthavecaffeine.recipeapp.api.v1.model.UserListDTO;
import com.musthavecaffeine.recipeapp.domain.User;
import com.musthavecaffeine.recipeapp.services.UserService;
import com.musthavecaffeine.recipeapp.services.UserServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = {"UserController"})
@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {

	public static final String BASE_URL = "/api/v1/user";
	
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@ApiOperation(value = "Get a list of available users", response = List.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = 200, message = "Successfully retrieved list"),
//			@ApiResponse(code = 401, message = "Your are not authenticated"),
//			@ApiResponse(code = 403, message = "You are not authorized to view the resource "),
//			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
//	})
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
    public UserListDTO list(Model model){
        return new UserListDTO(userService.getAllUsers());
	}
	
    @ApiOperation(value = "Find a user by ID",response = User.class)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserById(@PathVariable Long id){
    	return userService.getUserById(id);
    }

    @ApiOperation(value = "Add a new user")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createNewUser(@RequestBody UserDTO userDto){
        return userService.createNewUser(userDto);
        
//        URI location = ServletUriComponentsBuilder
//				.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(result.getId()).toUri();
//
//		log.info(result.toString());
//		
//		return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "Update a user by ID")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDto){
    	return userService.saveUserByDto(id, userDto);
    }

    @ApiOperation(value = "Delete a user by ID")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userService.deleteUserById(id);
    }
}
