package com.musthavecaffeine.recipeapp.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.musthavecaffeine.recipeapp.api.v1.mapper.IngredientMapper;
import com.musthavecaffeine.recipeapp.api.v1.model.IngredientDTO;
import com.musthavecaffeine.recipeapp.api.v1.model.IngredientListDTO;
import com.musthavecaffeine.recipeapp.domain.Ingredient;
import com.musthavecaffeine.recipeapp.repositories.IngredientRepository;




public class IngredientServiceTest {

	public static final Long ID_1 = 1L;
	public static final String NAME_1 = "Rice";
	public static final Long ID_2 = 2L;
	public static final String NAME_2 = "Suggar";

	@Mock
	IngredientRepository ingredientRepository;

	IngredientService ingredientService;

	
	@Before
	public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        ingredientService = new IngredientServiceImpl(IngredientMapper.INSTANCE, ingredientRepository);
	}
        
	
	@Test
	public void getIngredientById() {
		
		// given
		Ingredient ingredient = getIngredient1();

		given(ingredientRepository.findById(anyLong())).willReturn(Optional.of(ingredient));

		// when
		IngredientDTO ingredientDto = ingredientService.getIngredientById(ID_1);
		
		// then
		then(ingredientRepository).should(times(1)).findById(anyLong());
		
		assertThat(ingredientDto.getId(), is(equalTo(ID_1)));
		assertThat(ingredientDto.getName(), is(equalTo(NAME_1)));
	}
	
//	@Test
//	public void getIngredientByIdNotFound() throws Exception {
//		
//		// given
//		given(ingredientRepository.findById(anyLong())).willReturn(Optional.empty());
//
//		// when
//		IngredientDTO ingredientDto = ingredientService.getIngredientById(ID_1);
//		
//		// then
//		then(ingredientRepository).should(times(1)).findById(anyLong());
//	}

	@Test
	public void getIngredientByName() {
		
		// given
		Ingredient ingredient = getIngredient1();
		
		given(ingredientRepository.findByName(anyString())).willReturn(ingredient);
		
		// when
		IngredientDTO ingredientDto = ingredientService.getIngredientByName(NAME_1);
		
		// then
		then(ingredientRepository).should(times(1)).findByName(anyString());

		assertThat(ingredientDto.getId(), is(equalTo(ID_1)));
		assertThat(ingredientDto.getName(), is(equalTo(NAME_1)));
	}

	@Test
	public void getAllIngredients() {
		
		// given
		List<Ingredient> ingredients = Arrays.asList(getIngredient1(), getIngredient2());
		
		given(ingredientRepository.findAll()).willReturn(ingredients);
		
		// when
		IngredientListDTO ingredientListDtos = ingredientService.getAllIngredients();
		
		// then
		then(ingredientRepository).should(times(1)).findAll();
		assertThat(ingredientListDtos.getIngredients().size(), is(equalTo(2)));
	}
	
	 @Test
	public void createNewIngredient() throws Exception {
		// given
		IngredientDTO ingredientDto = new IngredientDTO();
		ingredientDto.setName(NAME_1);
		
		Ingredient ingredient = getIngredient1();
		
		given(ingredientRepository.save(any(Ingredient.class))).willReturn(ingredient);
		
		// when
		IngredientDTO savedIngredientDto = ingredientService.createNewIngredient(ingredientDto);
		
		// then
		// 'should' defaults to times = 1
		then(ingredientRepository).should().save(any(Ingredient.class));
		assertThat(savedIngredientDto.getId(), is(equalTo(ID_1)));
	}

	@Test
	public void saveIngredientByDTO() throws Exception {
		// given
		IngredientDTO ingredientDto = new IngredientDTO();
		ingredientDto.setName(NAME_1);
		
		Ingredient ingredient = getIngredient1();
		
		given(ingredientRepository.save(any(Ingredient.class))).willReturn(ingredient);
		
		// when
		IngredientDTO savedIngredientDto = ingredientService.saveIngredientByDto(ID_1, ingredientDto);
		
		// then
		// 'should' defaults to times = 1
		then(ingredientRepository).should().save(any(Ingredient.class));
		assertThat(savedIngredientDto.getId(), is(equalTo(ID_1)));
	}	 
	
//	@Test
//	public void deleteVendorById() throws Exception {
//		
//		// when
//		ingredientService.deleteIngredientById(ID_1);
//		
//		// then
//        then(ingredientService).should().deleteIngredientById(anyLong());
//	}	
	
	private Ingredient getIngredient1() {
		Ingredient ingredient = new Ingredient();
		ingredient.setId(ID_1);
		ingredient.setName(NAME_1);
		
		return ingredient;
	}

	private Ingredient getIngredient2() {
		Ingredient ingredient = new Ingredient();
		ingredient.setId(ID_2);
		ingredient.setName(NAME_2);
		
		return ingredient;
	}
}
