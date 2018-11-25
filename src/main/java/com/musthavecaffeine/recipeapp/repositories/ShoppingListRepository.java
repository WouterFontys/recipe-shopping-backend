package com.musthavecaffeine.recipeapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musthavecaffeine.recipeapp.domain.ShoppingList;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

}
