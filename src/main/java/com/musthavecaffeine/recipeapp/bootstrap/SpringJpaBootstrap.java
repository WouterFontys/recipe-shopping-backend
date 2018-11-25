package com.musthavecaffeine.recipeapp.bootstrap;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.musthavecaffeine.recipeapp.domain.Ingredient;
import com.musthavecaffeine.recipeapp.domain.IngredientRow;
import com.musthavecaffeine.recipeapp.domain.Recipe;
import com.musthavecaffeine.recipeapp.repositories.IngredientRepository;
import com.musthavecaffeine.recipeapp.repositories.IngredientRowRepository;
import com.musthavecaffeine.recipeapp.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SpringJpaBootstrap implements CommandLineRunner {

	private final IngredientRepository ingredientRepository;
	
	private final IngredientRowRepository ingredientRowRepository;
	
	private final RecipeRepository recipeRepository;
	

	public SpringJpaBootstrap(IngredientRepository ingredientRepository, IngredientRowRepository ingredientRowRepository, RecipeRepository recipeRepository) {
		this.ingredientRepository= ingredientRepository;
		this.ingredientRowRepository = ingredientRowRepository;
		this.recipeRepository = recipeRepository;
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		
		loadIngredients();
		
		loadRecipes();
		
		loadIngredientRows();
	}

	private void loadIngredients() {
		log.info("Loading ingredients");
		Ingredient i = new Ingredient();
		i.setName("Rijst");
		ingredientRepository.save(i);
		
		i = new Ingredient();
		i.setName("Sjalotten");
		ingredientRepository.save(i);
		
		i = new Ingredient();
		i.setName("Knoflook");
		ingredientRepository.save(i);
		
		i = new Ingredient();
		i.setName("Rode peper");
		ingredientRepository.save(i);

		i = new Ingredient();
		i.setName("Vanillesuiker");
		ingredientRepository.save(i);
		
		i = new Ingredient();
		i.setName("Ei");
		ingredientRepository.save(i);
		
		i = new Ingredient();
		i.setName("Bloem");
		ingredientRepository.save(i);

		i = new Ingredient();
		i.setName("Boter");
		ingredientRepository.save(i);
	}
	
	private void loadRecipes() {
		log.info("Loading recipes");
		Recipe nasi = new Recipe();
		Recipe kaiserschmarrn = new Recipe();
		
		Set<IngredientRow> nasiIngredients = new HashSet<IngredientRow>();
		Set<IngredientRow> kaiserschmarrnIngredients = new HashSet<IngredientRow>();

		nasi.setName("Nasi Goreng");
		nasi.setDescription("Hak de sjalotten, knoflook, rode peper (met of zonder zaadjes) en laos grof. Maal fijn in een hakmolen of keukenmachine met de trassi en water. Je kunt dit uiteraard ook met de hand fijnhakken.");
		nasi.setImageUrl(
				"https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_shirt-rf412049699c14ba5b68bb1c09182bfa2_8nax2_512.jpg");
		nasi.setPreparationTime(20l);
		nasi.setIngredientsRows(nasiIngredients);
		recipeRepository.save(nasi);
		
		
		kaiserschmarrn.setName("Kaiserschmarrn met abrikozencompote");
		kaiserschmarrn.setDescription("Splits de eieren en zet het eiwit apart in de koelkast. Meng de eidooiers met de bloem, water, vanillesuiker en zout in een kom tot een beslag. Eventueel kun je er nog wat rasp van een ½ citroen of sinaasappel aan toevoegen.");
		kaiserschmarrn.setImageUrl(
				"https://www.francescakookt.nl/wp-content/uploads/kaiserschmarrn-met-abrikozencompote-1.jpg");
		kaiserschmarrn.setPreparationTime(10l);
		kaiserschmarrn.setIngredientsRows(kaiserschmarrnIngredients);
		recipeRepository.save(kaiserschmarrn);
	}
	

	private void loadIngredientRows() {
		log.info("Loading ingredientRows");
		//Nasi Goreng
		IngredientRow row = new IngredientRow();
		row.setRecipe(recipeRepository.findByName("Nasi Goreng"));
		row.setIngredient(ingredientRepository.findByName("Rijst"));
		row.setAmount("200");
//		row.setUnit(IngredientUnitType.GRAM);
	
		ingredientRowRepository.save(row);
		
		row = new IngredientRow();
		row.setRecipe(recipeRepository.findByName("Nasi Goreng"));
		row.setIngredient(ingredientRepository.findByName("Sjalotten"));
		row.setAmount("5");
//		row.setUnit(IngredientUnitType.PIECES);
		ingredientRowRepository.save(row);

		row = new IngredientRow();
		row.setRecipe(recipeRepository.findByName("Nasi Goreng"));
		row.setIngredient(ingredientRepository.findByName("Knoflook"));
		row.setAmount("1");
//		row.setUnit(IngredientUnitType.PIECES);
		ingredientRowRepository.save(row);

		row = new IngredientRow();
		row.setRecipe(recipeRepository.findByName("Nasi Goreng"));
		row.setIngredient(ingredientRepository.findByName("Rode peper"));
		row.setAmount("0,5");
//		row.setUnit(IngredientUnitType.PIECES);
		ingredientRowRepository.save(row);

		
		// Kaiserschmarrn met abrikozencompote
		row = new IngredientRow();
		row.setRecipe(recipeRepository.findByName("Kaiserschmarrn met abrikozencompote"));
		row.setIngredient(ingredientRepository.findByName("Vanillesuiker"));
		row.setAmount("50");
//		row.setUnit(IngredientUnitType.GRAM);
		ingredientRowRepository.save(row);
		
		row = new IngredientRow();
		row.setRecipe(recipeRepository.findByName("Kaiserschmarrn met abrikozencompote"));
		row.setIngredient(ingredientRepository.findByName("Ei"));
		row.setAmount("3");
//		row.setUnit(IngredientUnitType.PIECES);
		ingredientRowRepository.save(row);
		
		row = new IngredientRow();
		row.setRecipe(recipeRepository.findByName("Kaiserschmarrn met abrikozencompote"));
		row.setIngredient(ingredientRepository.findByName("Bloem"));
		row.setAmount("250");
//		row.setUnit(IngredientUnitType.GRAM);
		ingredientRowRepository.save(row);

		row = new IngredientRow();
		row.setRecipe(recipeRepository.findByName("Kaiserschmarrn met abrikozencompote"));
		row.setIngredient(ingredientRepository.findByName("Boter"));
		row.setAmount("20");
//		row.setUnit(IngredientUnitType.GRAM);
		ingredientRowRepository.save(row);

	}
}
