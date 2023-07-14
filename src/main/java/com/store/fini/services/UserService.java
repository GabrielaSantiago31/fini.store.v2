package com.store.fini.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.store.fini.entities.Address;
import com.store.fini.entities.User;
import com.store.fini.entities.dto.AddressInputDto;
import com.store.fini.entities.dto.UserInputDto;
import com.store.fini.entities.dto.UserOutputDto;
import com.store.fini.exceptions.BusinessErrorException;
import com.store.fini.repositories.AddressRepository;
import com.store.fini.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	public List<UserOutputDto> getUsers(){
		
		List<User> users = userRepository.findAll();
		
		List<UserOutputDto> usersDto = mapper.map(users, new TypeToken<List<UserOutputDto>>() {}.getType());
		
		return usersDto;
	}
	
	public UserOutputDto getAnUserById(Long id) {
		
		Optional<User> optional = userRepository.findById(id);
		
		if(optional.isEmpty()) {
			throw new BusinessErrorException(HttpStatus.NOT_FOUND, "User not found.");
		}
		
		User userDb = optional.get();
		
		UserOutputDto outputDto = mapper.map(userDb, UserOutputDto.class);
		
		return outputDto;
	}
	
	public UserOutputDto save(UserInputDto userInputDto) {
		
		try {
			User user = mapper.map(userInputDto, User.class);
			
			addressRepository.save(user.getAddress());
			
			User userDb = userRepository.save(user);
			
			UserOutputDto outputDto = mapper.map(userDb, UserOutputDto.class);
			
			userRepository.save(user);
			
			return outputDto;
			
		}catch(DataIntegrityViolationException e) {
			
			throw new BusinessErrorException(HttpStatus.BAD_REQUEST, "User is already registered");
		}
		
	}
	
	public void update(UserInputDto newUser, Long id) {
		
		Optional<Address> opt = addressRepository.findById(id);

		if(!opt.isPresent()) {
			throw new BusinessErrorException(HttpStatus.NOT_FOUND, "Address not found");
		}

		Address addressDb = opt.get();
		addressRepository.save(opt.get());
		
		mapper.map(newUser.getAddress(), addressDb);

		Optional<User> optional = userRepository.findById(id);
		
		if(optional.isEmpty()) {
			throw new BusinessErrorException(HttpStatus.NOT_FOUND, "User not found.");
		}
		
		User userDb = optional.get();
		
		mapper.map(newUser, userDb);	
		userRepository.save(userDb);
		
	}
	
	public void delete(Long id) {
		if(!userRepository.existsById(id)) {
			throw new BusinessErrorException(HttpStatus.NOT_FOUND, "User not found.");
		}
		userRepository.deleteById(id);
	}
	
	
}
