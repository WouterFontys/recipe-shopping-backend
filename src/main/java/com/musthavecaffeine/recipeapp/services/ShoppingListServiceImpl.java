package com.musthavecaffeine.recipeapp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.musthavecaffeine.recipeapp.api.v1.mapper.ShoppingListMapper;
import com.musthavecaffeine.recipeapp.api.v1.model.ShoppingListDTO;
import com.musthavecaffeine.recipeapp.domain.ShoppingList;
import com.musthavecaffeine.recipeapp.repositories.ShoppingListRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ShoppingListServiceImpl implements ShoppingListService {

	private final ShoppingListMapper shoppingListMapper;
	private final ShoppingListRepository shoppingListRepository;
	
	
	public ShoppingListServiceImpl(ShoppingListMapper shoppingListMapper, ShoppingListRepository shoppingListRepository) {
		this.shoppingListMapper = shoppingListMapper;
		this.shoppingListRepository = shoppingListRepository;
	}

	@Override
	public List<ShoppingListDTO> getAllShoppingLists() {
		log.debug("getAllShoppingLists called");
		return shoppingListRepository
				.findAll()
                .stream()
                .map(shoppingList -> {
                   ShoppingListDTO shoppingListDTO = shoppingListMapper.shoppingListToShoppingListDto(shoppingList);
//                   shoppingListDTO.setShoppingListUrl(getShoppingListUrl(shoppingList.getId()));
                   return shoppingListDTO;
                })
                .collect(Collectors.toList());
	}

	@Override
	public ShoppingListDTO getShoppingListById(Long id) {
		log.debug("getShoppingListById called with id: {}", id);
		return shoppingListRepository.findById(id)
				.map(shoppingListMapper::shoppingListToShoppingListDto)
//				.map(shoppingListDTO -> {
//					// set API URL
//					shoppingListDTO.setShoppingListUrl(getShoppingListUrl(id));
//					return shoppingListDTO;
//				})
				.orElseThrow(ResourceNotFoundException::new);
	}

	@Override
	public ShoppingListDTO createNewShoppingList(ShoppingListDTO shoppingListDto) {
		log.debug("createNewShoppingList called: {}", shoppingListDto.toString());
		return saveAndReturnDto(shoppingListMapper.shoppingListDtoToShoppingList(shoppingListDto));
	}
	
	@Override
	public ShoppingListDTO saveShoppingListByDto(Long id, ShoppingListDTO shoppingListDto) {
		log.debug("saveShoppingListByDto called: {}", shoppingListDto.toString());
		ShoppingList shoppingList = shoppingListMapper.shoppingListDtoToShoppingList(shoppingListDto);
		shoppingList.setId(id);
		return saveAndReturnDto(shoppingList);
	}	
	
	@Override
	public void deleteShoppingListById(Long id) {
		log.debug("deleteShoppingListById called with id: {}", id);
		shoppingListRepository.deleteById(id);
	}
	
	private ShoppingListDTO saveAndReturnDto(ShoppingList shoppingList) {
		ShoppingList savedShoppingList = shoppingListRepository.save(shoppingList);
		ShoppingListDTO returnDto = shoppingListMapper.shoppingListToShoppingListDto(savedShoppingList);
		return returnDto;
	}

}
