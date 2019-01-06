package com.musthavecaffeine.recipeapp.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserDto {

	@ApiModelProperty(notes = "The user id", required = false)
	private Long id;
	
	@ApiModelProperty(notes = "The user name", required = true)
	private String name;
}
