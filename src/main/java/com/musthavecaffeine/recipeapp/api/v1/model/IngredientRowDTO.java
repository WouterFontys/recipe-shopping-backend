package com.musthavecaffeine.recipeapp.api.v1.model;

import com.musthavecaffeine.recipeapp.domain.Ingredient;
import com.musthavecaffeine.recipeapp.domain.Recipe;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientRowDTO {

	@ApiModelProperty(notes = "The database generated ingredient ID")
	private Long id;
	
	@ApiModelProperty(notes = "The ingredient amount", required = true)
	private String amount;
	
//	@ApiModelProperty(notes = "The ingredient amount unit", required = true)
//	@Enumerated(value = EnumType.STRING)
//	private IngredientUnitType unit;
	
	@ApiModelProperty(notes = "The ingredient", required = true)
	private Ingredient ingredient;
	
	@ApiModelProperty(notes = "The recipe", required = true)
	private Recipe recipe;

}
