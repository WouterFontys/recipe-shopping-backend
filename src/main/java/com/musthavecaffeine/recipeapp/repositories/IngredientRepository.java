package com.musthavecaffeine.recipeapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musthavecaffeine.recipeapp.domain.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

	Optional<Ingredient> findByName(String name);
}
