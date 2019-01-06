package com.musthavecaffeine.recipeapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.musthavecaffeine.recipeapp.api.v1.mapper.ShoppingListMapper;
import com.musthavecaffeine.recipeapp.api.v1.model.ShoppingListDto;
import com.musthavecaffeine.recipeapp.domain.ShoppingList;
import com.musthavecaffeine.recipeapp.domain.User;
import com.musthavecaffeine.recipeapp.repositories.ShoppingListRepository;
import com.musthavecaffeine.recipeapp.repositories.UserRepository;
import com.musthavecaffeine.recipeapp.services.exceptions.ResourceNotFoundException;
import com.musthavecaffeine.recipeapp.services.exceptions.UnauthorizedException;

@Service
@Transactional
public class ShoppingListServiceImpl implements ShoppingListService{

	private final ShoppingListRepository shoppingListRepository;
	
	private final UserRepository userRepository;
	
	private final ShoppingListMapper shoppingListMapper;
	
	
	
	public ShoppingListServiceImpl(ShoppingListRepository shoppingListRepository, UserRepository userRepository,
			ShoppingListMapper shoppingListMapper) {
		this.shoppingListRepository = shoppingListRepository;
		this.userRepository = userRepository;
		this.shoppingListMapper = shoppingListMapper;
	}

	@Override
	public List<ShoppingListDto> getAllShoppingLists(Long userId) {
		ArrayList<ShoppingListDto> shoppingListDtos = new ArrayList<ShoppingListDto>();
		
		List<ShoppingList> shoppingLists = shoppingListRepository.findAll();
		for (ShoppingList shoppingList : shoppingLists) {
			// only expose shoppingLists that the user is allowed to see
			// alternatively we could have created a proper query to only
			// get those shoppingLists from the db
			if (shoppingList.getUser().getId() == userId) {
				shoppingListDtos.add(shoppingListMapper.shoppingListToShoppingListDto(shoppingList));
			}
		}
		
		return shoppingListDtos;

	}

	@Override
	public ShoppingListDto getShoppingListById(Long id, Long userId) {
		ShoppingList shoppingList = shoppingListRepository
				.findById(id)
				.orElseThrow(ResourceNotFoundException::new);
		
		if (shoppingList.getUser().getId() != userId) {
			throw new UnauthorizedException();
		}
		
		return shoppingListMapper.shoppingListToShoppingListDto(shoppingList);
	}

	@Override
	public ShoppingListDto createNewShoppingList(ShoppingListDto shoppingListDto, Long userId) {
		User user = userRepository
				.findById(userId)
				.orElseThrow(UnauthorizedException::new);
		
		ShoppingList shoppingList = shoppingListMapper.shoppingListDtoToShoppingList(user, shoppingListDto);
		return shoppingListMapper.shoppingListToShoppingListDto(shoppingListRepository.save(shoppingList));
	}

	@Override
	public ShoppingListDto updateShoppingList(ShoppingListDto shoppingListDto, Long userId) {

		if (shoppingListDto.getUserId() != userId) {
			throw new UnauthorizedException();
		}
		
		ShoppingList shoppingList = shoppingListRepository
				.findById(shoppingListDto.getId())
				.orElseThrow(ResourceNotFoundException::new);
		
		shoppingListMapper.shoppingListDtoToShoppingList(shoppingList.getUser(), shoppingListDto, shoppingList);
		
		shoppingListDto = shoppingListMapper.shoppingListToShoppingListDto(shoppingListRepository.save(shoppingList));

		return shoppingListDto;
	}

	@Override
	public void deleteShoppingListById(Long id, Long userId) {
		
		ShoppingList shoppingList = shoppingListRepository
				.findById(id)
				.orElseThrow(ResourceNotFoundException::new);
		
		if (shoppingList.getUser().getId() == userId) {
			shoppingListRepository.deleteById(id);
		} else {
			throw new UnauthorizedException();
		}
		
	}

}
