package com.musthavecaffeine.recipeapp.api.v1.model;

import com.musthavecaffeine.recipeapp.domain.User;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingListDTO {

	@ApiModelProperty(notes = "The database generated shoppinglist ID")
	private Long id;
	
	@ApiModelProperty(notes = "The user the shoppinglist belongs to", required = true)
	private User user;
	
	@ApiModelProperty(notes = "The shoppinglist name", required = true)
	private String name;
}
