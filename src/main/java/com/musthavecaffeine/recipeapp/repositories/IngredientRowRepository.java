package com.musthavecaffeine.recipeapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musthavecaffeine.recipeapp.domain.IngredientRow;

public interface IngredientRowRepository extends JpaRepository<IngredientRow, Long> {

}
