package com.musthavecaffeine.recipeapp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.musthavecaffeine.recipeapp.api.v1.mapper.UserMapper;
import com.musthavecaffeine.recipeapp.api.v1.model.UserDto;
import com.musthavecaffeine.recipeapp.domain.User;
import com.musthavecaffeine.recipeapp.repositories.UserRepository;
import com.musthavecaffeine.recipeapp.services.exceptions.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;
	private final UserRepository userRepository;

	public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
		this.userMapper = userMapper;
		this.userRepository = userRepository;
	}

	@Override
	public List<UserDto> getAllUsers() {
		return userRepository
				.findAll()
                .stream()
                .map(user -> {
                   UserDto userDto = userMapper.userToUserDto(user);
//                   userDTO.setUserUrl(getUserUrl(user.getId()));
                   return userDto;
                })
                .collect(Collectors.toList());

	}

	@Override
	public UserDto getUserById(Long id) {
		return userRepository.findById(id)
				.map(userMapper::userToUserDto)
				.orElseThrow(ResourceNotFoundException::new);
	}

	@Override
	public UserDto createNewUser(UserDto userDto) {
		return saveAndReturnDto(userMapper.userDtoToUser(userDto));
	}

	@Override
	public UserDto updateUser(Long userId, UserDto userDto) {
		
		if (userId != userDto.getId()) {
			throw new RuntimeException("Unautorized");
		}
		
		User user = userRepository
			.findById(userDto.getId())
			.orElseThrow(ResourceNotFoundException::new);
		
		return saveAndReturnDto(user);
	}

	@Override
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}
	
	
	private UserDto saveAndReturnDto(User user) {
		userRepository.save(user);
		return userMapper.userToUserDto(user);
	}
}
