package com.musthavecaffeine.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.musthavecaffeine.recipeapp.domain.Recipe;

@RepositoryRestResource
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {

}
