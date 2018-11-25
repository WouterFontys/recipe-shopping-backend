package com.musthavecaffeine.recipeapp.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	@ApiModelProperty(notes = "The database generated user ID")
	private Long id;
	
	@ApiModelProperty(notes = "The username", required = true)
	private String name;

}
