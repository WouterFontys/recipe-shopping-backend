package com.musthavecaffeine.recipeapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
public class AntiIngredient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "The database generated anti-ingredient ID")
	private Long id;
	
//	private User user;
//	private Ingredient ingredient;
}
