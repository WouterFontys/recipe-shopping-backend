package com.musthavecaffeine.recipeapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
public class Favorite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "The database generated favorite ID")
	private Long id;
	
	@Version
	@ApiModelProperty(notes = "The auto-generated version of the favorite")
	private Integer version;
	
//	@ManyToOne
//	private Recipe recipe;
	
//	@ManyToOne
//	private User user;
}
