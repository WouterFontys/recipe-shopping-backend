package com.musthavecaffeine.recipeapp.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class IngredientDto {

	@ApiModelProperty(notes = "The ingredient id", required = false)
	private Long id;

	@ApiModelProperty(notes = "The ingredient name", required = true)
	private String name;
	
	@ApiModelProperty(notes = "The ingredient amount", required = true)
	private Float amount;
	
//	@ApiModelProperty(notes = "The ingredient amount unit", required = true)
//	@Enumerated(value = EnumType.STRING)
//	private IngredientUnitType unit;
}
