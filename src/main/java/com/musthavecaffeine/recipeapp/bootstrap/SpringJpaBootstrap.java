package com.musthavecaffeine.recipeapp.bootstrap;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.musthavecaffeine.recipeapp.domain.Ingredient;
import com.musthavecaffeine.recipeapp.domain.IngredientRow;
import com.musthavecaffeine.recipeapp.domain.IngredientRow.IngredientUnitType;
import com.musthavecaffeine.recipeapp.domain.Recipe;
import com.musthavecaffeine.recipeapp.repositories.IngredientRepository;
import com.musthavecaffeine.recipeapp.repositories.RecipeRepository;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger logger = LogManager.getLogger(SpringJpaBootstrap.class);
	
	private IngredientRepository ingredientRepository;
	
	private RecipeRepository recipeRepository;
	

	@Autowired
	public void setRecipeRepository(IngredientRepository ingredientRepository, RecipeRepository recipeRepository) {
		this.ingredientRepository= ingredientRepository;
		this.recipeRepository = recipeRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		loadRecipes();
	}

	private void loadRecipes() {
		
		Recipe nasi = new Recipe();
		Recipe kaiserschmarrn = new Recipe();
		
		Set<IngredientRow> nasiIngredients = new HashSet<IngredientRow>();
		Set<IngredientRow> kaiserschmarrnIngredients = new HashSet<IngredientRow>();
		
		Ingredient i = new Ingredient();
		i.setName("Rijst");
		ingredientRepository.save(i);
		
		IngredientRow row = new IngredientRow();
		row.setRecipe(nasi);
		row.setIngredient(i);
		row.setAmount("200");
		row.setUnit(IngredientUnitType.GRAM);

		nasiIngredients.add(row);
		
		
		i = new Ingredient();
		i.setName("Sjalotten");
		ingredientRepository.save(i);

		row = new IngredientRow();
		row.setRecipe(nasi);
		row.setIngredient(i);
		row.setAmount("5");
		row.setUnit(IngredientUnitType.PIECES);

		nasiIngredients.add(row);

		
		i = new Ingredient();
		i.setName("knoflook");
		ingredientRepository.save(i);

		row = new IngredientRow();
		row.setRecipe(nasi);
		row.setIngredient(i);
		row.setAmount("1");
		row.setUnit(IngredientUnitType.PIECES);

		nasiIngredients.add(row);
		
		
		i = new Ingredient();
		i.setName("Rode peper");
		ingredientRepository.save(i);

		row = new IngredientRow();
		row.setRecipe(nasi);
		row.setIngredient(i);
		row.setAmount("0,5");
		row.setUnit(IngredientUnitType.PIECES);

		nasiIngredients.add(row);

		
		


		
		i = new Ingredient();
		i.setName("Ei");
		ingredientRepository.save(i);
		
		row = new IngredientRow();
		row.setRecipe(kaiserschmarrn);
		row.setIngredient(i);
		row.setAmount("5");
		row.setUnit(IngredientUnitType.PIECES);

		kaiserschmarrnIngredients.add(row);		
		
		i = new Ingredient();
		i.setName("Bloem");
		ingredientRepository.save(i);

		row = new IngredientRow();
		row.setRecipe(kaiserschmarrn);
		row.setIngredient(i);
		row.setAmount("250");
		row.setUnit(IngredientUnitType.GRAM);

		kaiserschmarrnIngredients.add(row);		

		
		i = new Ingredient();
		i.setName("Vanillesuiker");
		ingredientRepository.save(i);
		
		row = new IngredientRow();
		row.setRecipe(kaiserschmarrn);
		row.setIngredient(i);
		row.setAmount("20");
		row.setUnit(IngredientUnitType.GRAM);

		kaiserschmarrnIngredients.add(row);	
		
		
		

		nasi.setName("Nasi Goreng");
		nasi.setDescription("Hak de sjalotten, knoflook, rode peper (met of zonder zaadjes) en laos grof. Maal fijn in een hakmolen of keukenmachine met de trassi en water. Je kunt dit uiteraard ook met de hand fijnhakken.");
		nasi.setImageUrl(
				"https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_shirt-rf412049699c14ba5b68bb1c09182bfa2_8nax2_512.jpg");
		nasi.setPreperationTime(20);
		nasi.setIngredients(nasiIngredients);
		recipeRepository.save(nasi);
		
		
		kaiserschmarrn.setName("Kaiserschmarrn met abrikozencompote");
		kaiserschmarrn.setDescription("Splits de eieren en zet het eiwit apart in de koelkast. Meng de eidooiers met de bloem, water, vanillesuiker en zout in een kom tot een beslag. Eventueel kun je er nog wat rasp van een ½ citroen of sinaasappel aan toevoegen.");
		kaiserschmarrn.setImageUrl(
				"https://www.francescakookt.nl/wp-content/uploads/kaiserschmarrn-met-abrikozencompote-1.jpg");
		kaiserschmarrn.setPreperationTime(10);
		kaiserschmarrn.setIngredients(kaiserschmarrnIngredients);
		recipeRepository.save(kaiserschmarrn);
	}

}
