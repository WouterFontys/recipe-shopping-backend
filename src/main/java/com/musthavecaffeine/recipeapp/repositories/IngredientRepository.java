package com.musthavecaffeine.recipeapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musthavecaffeine.recipeapp.domain.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

	Ingredient findByName(String name);
}
