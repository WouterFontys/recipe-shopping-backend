package com.musthavecaffeine.recipeapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.musthavecaffeine.recipeapp.domain.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

	Recipe findByName(String name);
	
	@Override
	Optional<Recipe> findById(Long id);
}
