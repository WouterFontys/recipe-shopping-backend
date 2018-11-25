package com.musthavecaffeine.recipeapp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.musthavecaffeine.recipeapp.api.v1.mapper.UserMapper;
import com.musthavecaffeine.recipeapp.api.v1.model.UserDTO;
import com.musthavecaffeine.recipeapp.domain.User;
import com.musthavecaffeine.recipeapp.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;
	private final UserRepository userRepository;

	public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
		this.userMapper = userMapper;
		this.userRepository = userRepository;
	}

	@Override
	public List<UserDTO> getAllUsers() {
		log.debug("getAllUsers called");
		return userRepository
				.findAll()
                .stream()
                .map(user -> {
                   UserDTO userDto = userMapper.userToUserDto(user);
//                   userDTO.setUserUrl(getUserUrl(user.getId()));
                   return userDto;
                })
                .collect(Collectors.toList());
	}

	@Override
	public UserDTO getUserById(Long id) {
		log.debug("getIngredientById called with id: {}", id);
		return userRepository.findById(id)
				.map(userMapper::userToUserDto)
				.orElseThrow(ResourceNotFoundException::new);
	}

	@Override
	public UserDTO createNewUser(UserDTO userDto) {
		log.debug("createNewUser called: {}", userDto.toString());
		return saveAndReturnDto(userMapper.userDtoToUser(userDto));
	}
	
	@Override
	public UserDTO saveUserByDto(Long id, UserDTO userDto) {
		log.debug("saveUserByDto called: {}", userDto.toString());
		User user = userMapper.userDtoToUser(userDto);
		user.setId(id);
		return saveAndReturnDto(user);
	}

	@Override
	public void deleteUserById(Long id) {
		log.debug("deleteUserById called with id: {}", id);
		userRepository.deleteById(id);
	}

	private UserDTO saveAndReturnDto(User user) {
		User savedUser = userRepository.save(user);
		UserDTO returnDto = userMapper.userToUserDto(savedUser);
		return returnDto;
	}

}
