package com.musthavecaffeine.recipeapp.repositories;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;

import org.springframework.stereotype.Repository;

import com.musthavecaffeine.recipeapp.domain.Recipe;
import com.musthavecaffeine.recipeapp.domain.RecipeDao;


@Repository
public class RecipeDaoRepository {

	@PersistenceContext	
	private EntityManager entityManager;
	

	public List<RecipeDao> findById(Long id) {
		String sql = "SELECT "
				+ "recipe.id as id, "
				+ "recipe.name, "
				+ "recipe.description, "
				+ "recipe.image_url, "
				+ "recipe.preparation_time, "
				+ "recipe.private_recipe, "
				+ "recipe.number_of_one_star_ratings, "
				+ "recipe.number_of_two_star_ratings, "
				+ "recipe.number_of_three_star_ratings, "
				+ "recipe.number_of_four_star_ratings, "
				+ "recipe.number_of_five_star_ratings, "
				+ "ingredient.id as ingredient_id, "
				+ "ingredient.name as ingredient_name, "
				+ "ingredient_row.amount as ingredient_amount "
				+ "FROM recipe LEFT JOIN ingredient_row ON recipe.id = ingredient_row.recipe_id "
				+ "JOIN ingredient ON ingredient_row.ingredient_id = ingredient.id "
				+ "WHERE recipe.id = :id";		
	
	
//		List<Object[]> results = entityManager.createNativeQuery(sql).setParameter("id", id).getResultList();
//		
//		results.stream().forEach((record) -> {
//	        Long recipeId = ((BigInteger) record[0]).longValue();
//	        String recipeName = (String) record[1];
//	        Long ingredientId = ((BigInteger) record[10]).longValue();
//	        String ingredientName = (String) record[11];
//	        String ingredientAmount = (String) record[12];
//	        
//	        System.out.println("recipeId=[" + recipeId + "], recipeName=[" + recipeName +
//	        		"], ingredientId=[" + ingredientId + "], ingredientName=[" + ingredientName +
//	        		"], ingredientAmount=[" + ingredientAmount + "]");
//		});		
		
		return entityManager.createNativeQuery(sql, RecipeDao.class).setParameter("id", id).getResultList();
	}
	
}
