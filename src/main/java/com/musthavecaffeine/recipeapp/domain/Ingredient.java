package com.musthavecaffeine.recipeapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "The database generated ingredient ID")
	private Integer id;
	
	@Version
	@ApiModelProperty(notes = "The auto-generated version of the ingredient")
	private Integer version;
	
	@ApiModelProperty(notes = "The ingredient name", required = true)
	private String name;

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

	@Override
	public String toString( ) {
		return String.format(
                "Ingredient[id=%d, version=%d, name='%s']",
                id, version, name);
	}
}
