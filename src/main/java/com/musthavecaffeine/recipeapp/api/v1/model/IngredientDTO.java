package com.musthavecaffeine.recipeapp.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDTO {

	@ApiModelProperty(notes = "The database generated ingredient ID")
	private Long id;
	
	@ApiModelProperty(notes = "The ingredient name", required = true)
	private String name;

	@ApiModelProperty(notes = "The amount", required = true)
	private String amount;
	
	//TODO: add unit of measure
}
