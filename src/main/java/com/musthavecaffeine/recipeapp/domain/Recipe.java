package com.musthavecaffeine.recipeapp.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Recipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "The database generated recipe ID")
	private Integer id;
	
	@Version
	@ApiModelProperty(notes = "The auto-generated version of the recipe")
	private Integer version;
	
	@ApiModelProperty(notes = "The recipe name", required = true)
	private String name;
	
	@ApiModelProperty(notes = "The recipe description", required = true)
	private String description;
	
	@ApiModelProperty(notes = "The image URL of the dish")
	private String imageUrl;
	
	@ApiModelProperty(notes = "The preperation time of the dish (in minutes)")
	private Integer preperationTime;
	
	@ApiModelProperty(notes = "The list of ingredients")
	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "recipe")
	private Set<IngredientRow> ingredients;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getPreperationTime() {
		return preperationTime;
	}

	public void setPreperationTime(Integer preperationTime) {
		this.preperationTime = preperationTime;
	}

	public Set<IngredientRow> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<IngredientRow> ingredients) {
		this.ingredients = ingredients;
	}
	
	@Override
	public String toString( ) {
		return String.format(
                "Recipe[id=%d, version=%d, name='%s', description='%s', imageUrl='%s', preperationTime=%d]",
                id, version, name, description, imageUrl, preperationTime);
	}
}
