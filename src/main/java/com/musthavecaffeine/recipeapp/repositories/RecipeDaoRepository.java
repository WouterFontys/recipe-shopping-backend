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
import org.springframework.transaction.annotation.Transactional;

import com.musthavecaffeine.recipeapp.domain.Recipe;
import com.musthavecaffeine.recipeapp.domain.RecipeDao;
import com.musthavecaffeine.recipeapp.services.RecipeServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class RecipeDaoRepository {

	@PersistenceContext	
	private EntityManager entityManager;
	
	// TODO: this needs to be adapted so that the user is taken into account 
	public List<RecipeDao> findAllPublicForUser() {
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
				+ "LEFT JOIN ingredient ON ingredient_row.ingredient_id = ingredient.id "
				+ "WHERE recipe.private_recipe != 'true' "
				//+ "OR recipe.user_id = :id "
				+ "ORDER BY recipe.id";
		
		return entityManager.createNativeQuery(sql, RecipeDao.class).getResultList();
//		return entityManager.createNativeQuery(sql, RecipeDao.class).setParameter("id", userId).getResultList();
	}

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
				+ "LEFT JOIN ingredient ON ingredient_row.ingredient_id = ingredient.id "
				+ "WHERE recipe.id = :id";		
		
		return entityManager.createNativeQuery(sql, RecipeDao.class).setParameter("id", id).getResultList();
	}
	
	@Transactional
	public void deleteById(Long id) {
		log.info("deleteById: {}", id);
		String sql = "DELETE FROM ingredient_row WHERE recipe_id = :id";
		entityManager.createNativeQuery(sql).setParameter("id", id).executeUpdate();

		sql = "DELETE FROM recipe WHERE id = :id";
		entityManager.createNativeQuery(sql).setParameter("id", id).executeUpdate();
	}

}
