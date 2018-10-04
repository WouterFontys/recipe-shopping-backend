package com.musthavecaffeine.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.musthavecaffeine.recipeapp.domain.Ingredient;

@RepositoryRestResource
public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

	Ingredient findByName(String name);
}
