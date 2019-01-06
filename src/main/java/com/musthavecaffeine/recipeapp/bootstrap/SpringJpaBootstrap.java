package com.musthavecaffeine.recipeapp.bootstrap;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.musthavecaffeine.recipeapp.domain.Ingredient;
import com.musthavecaffeine.recipeapp.domain.Recipe;
import com.musthavecaffeine.recipeapp.domain.RecipeIngredient;
import com.musthavecaffeine.recipeapp.domain.User;
import com.musthavecaffeine.recipeapp.repositories.IngredientRepository;
import com.musthavecaffeine.recipeapp.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SpringJpaBootstrap implements CommandLineRunner {
	
	@Autowired
	private EntityManager em;
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Autowired
	private RecipeRepository recipeRepository;
	
	@Override
	@Transactional
	public void run(String... args) throws Exception {

//		insertIngredients();
//		insertRecipes();
//		
//		addIngredientsToRecipe();
		
		Long id = 1L;
//		recipeRepository.deleteById(id);
		
//		ingredientRepository.deleteById(id);
		
//		Ingredient ingredient = ingredientRepository.findById(1L).get();
//		
//		Recipe recipe = recipeRepository.findById(2L).get();
//		recipe.addIngredient(ingredient, 2.0F);
//		recipeRepository.save(recipe);
//		
	


//		Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
//		Recipe recipe = optionalRecipe.get();
//		
//		Ingredient ui = ingredientRepository.findByName("Ui").get();
//		Ingredient wortel = ingredientRepository.findByName("Wortel").get();
//		Ingredient aardappel = ingredientRepository.findByName("Aardappel").get();
//
//		recipe.setName("Nasi zonder rijst");
//		recipe.addIngredient(ui, 2.0F);
//		recipe.addIngredient(wortel, 6.0F);
//		recipe.addIngredient(aardappel, 3.5F);
//
//		Ingredient rijst = ingredientRepository.findByName("Rijst").get();		
//		recipe.removeIngredient(rijst);
//		
//		log.info("Recipe: {}", recipe);


	}
	
	private void insertIngredients() {
		log.debug("Inserting ingredients into the database");
		
		Ingredient[] ingredients = {
				new Ingredient("Rijst"),
				new Ingredient("Sjalotten"),
				new Ingredient("Knoflook"),
				new Ingredient("Rode peper"),
				new Ingredient("Peper"),
				new Ingredient("Zout"),
				new Ingredient("Vanillesuiker"),
				new Ingredient("Ei"),
				new Ingredient("Bloem"),
				new Ingredient("Boter"),
				new Ingredient("Suiker")
		};
		
		for (Ingredient ingredient: ingredients) {
			em.persist(ingredient);
		}
	}

	private void insertRecipes() {
		log.debug("Inserting recipes into the database");
		
		User user1 = new User();
		user1.setId(1L);
		user1.setName("User1");
		
		Recipe recipe1 = new Recipe();
		recipe1.setName("Nasi Goreng");
		recipe1.setDescription("Hak de sjalotten, knoflook, rode peper (met of zonder zaadjes) en laos grof. Maal fijn in een hakmolen of keukenmachine met de trassi en water. Je kunt dit uiteraard ook met de hand fijnhakken.");
		recipe1.setImageUrl("https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_shirt-rf412049699c14ba5b68bb1c09182bfa2_8nax2_512.jpg");
		recipe1.setPreparationTime(20L);
		recipe1.setUser(user1);
		recipe1.setIsPrivate(false);

		Recipe recipe2 = new Recipe();
		recipe2.setName("Kaiserschmarrn met abrikozencompote");
		recipe2.setDescription("Splits de eieren en zet het eiwit apart in de koelkast. Meng de eidooiers met de bloem, water, vanillesuiker en zout in een kom tot een beslag. Eventueel kun je er nog wat rasp van een ½ citroen of sinaasappel aan toevoegen.");
		recipe2.setImageUrl("https://www.francescakookt.nl/wp-content/uploads/kaiserschmarrn-met-abrikozencompote-1.jpg");
		recipe2.setPreparationTime(10L);
		recipe2.setUser(user1);
		recipe2.setIsPrivate(false);
		
		Recipe[] recipes = { recipe1, recipe2 };
		
		for (Recipe recipe: recipes) {
			em.persist(recipe);
		}
	}
	
	private void addIngredientsToRecipe() {
		log.debug("Add ingredients to recipes");
		
		Recipe recipe1 = em.find(Recipe.class, 1L);
		Recipe recipe2 = em.find(Recipe.class, 2L);
		
		Ingredient ingredient1 = em.find(Ingredient.class, 1L);
		Ingredient ingredient2 = em.find(Ingredient.class, 2L);
		Ingredient ingredient3 = em.find(Ingredient.class, 3L);
		Ingredient ingredient4 = em.find(Ingredient.class, 4L);
		Ingredient ingredient5 = em.find(Ingredient.class, 5L);
		Ingredient ingredient6 = em.find(Ingredient.class, 6L);
		Ingredient ingredient7 = em.find(Ingredient.class, 7L);
		Ingredient ingredient8 = em.find(Ingredient.class, 8L);
		Ingredient ingredient9 = em.find(Ingredient.class, 9L);
		Ingredient ingredient10 = em.find(Ingredient.class, 10L);
		Ingredient ingredient11 = em.find(Ingredient.class, 11L);
		
		
		RecipeIngredient recipeIngredient = new RecipeIngredient(recipe1, ingredient1, 10.0F);
		em.merge(recipeIngredient);
		
//		recipe1.addIngredient(ingredient1, 10.0F);
//		recipe1.addIngredient(ingredient2, 3.0F);
//		recipe1.addIngredient(ingredient3, 1.0F);
//		recipe1.addIngredient(ingredient4, 0.5F);
//		recipe1.addIngredient(ingredient5, 4.0F);
//		
//		recipe2.addIngredient(ingredient2, 10.0F);
		
//		ingredient1.addRecipeIngredient(recipeIngredient);
		
	}
}
