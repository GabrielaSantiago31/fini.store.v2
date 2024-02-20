package io.github.GabrielaSantiago31.fini.store.v2.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.github.GabrielaSantiago31.fini.store.v2.exceptions.BusinessRuleException;
import io.github.GabrielaSantiago31.fini.store.v2.mail.EmailService;
import io.github.GabrielaSantiago31.fini.store.v2.models.User;
import io.github.GabrielaSantiago31.fini.store.v2.models.dto.request.UserRequestDto;
import io.github.GabrielaSantiago31.fini.store.v2.models.dto.response.UserResponseDto;
import io.github.GabrielaSantiago31.fini.store.v2.repository.UserRepository;
import io.github.GabrielaSantiago31.fini.store.v2.services.IUser;

@Service
public class UserService implements IUser, UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AddressService addressService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private ModelMapper mapper;
	
	@Value("${mail.user.registration.msg}")
	private String msgRegistration;
	
	@Value("${mail.welcome.msg}")
	private String msgWelcome;
	
	public UserResponseDto findById(Long id) {
		
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
		
		UserResponseDto responseDto = mapper.map(user, UserResponseDto.class);
		return responseDto;
	}
	

	public List<UserResponseDto> findAll(){
		
		List<UserResponseDto> responseDtos = mapper.map(userRepository.findAll(),new TypeToken<List<UserResponseDto>>() {}.getType());
		
		return responseDtos;
	}
	
	public UserResponseDto save(UserRequestDto userDto){
		
		User userFilled = addressService.fillAddressFields(userDto);
		
		if(userFilled.getCpf() == null) {
			throw new BusinessRuleException("CPF cannot be null.");
		}
		
		if(userFilled.getAddress() == null || userFilled.getAddress().getZipCode() == null) {
			throw new BusinessRuleException("Address cannot be null.");
		}
		
		if(userRepository.findByCpf(userFilled.getCpf()) != null) {
			throw new BusinessRuleException("This CPF is already registered.");
		}
		
		String encryptedPassword = new BCryptPasswordEncoder().encode(userFilled.getPassword());
		userFilled.setPassword(encryptedPassword);
		
		userRepository.save(userFilled);
		
		UserResponseDto responseDto = mapper.map(userFilled, UserResponseDto.class);
		
		emailService.sendEmail(userFilled.getEmail(),msgWelcome,msgRegistration);
		
		return responseDto;
	}
	
	public void delete(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
		
		userRepository.delete(user);
		
	}
	
	public void update(Long id, UserRequestDto userDto) {
		
		User oldUser = userRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
		
		User userFilled = addressService.fillAddressFields(userDto);
		
		userFilled.setId(oldUser.getId());
		
		
		if(userDto.getCpf() == null) {
			throw new BusinessRuleException("CPF cannot be null.");
		}
		
		if(userDto.getAddress() == null || userDto.getAddress().getZipCode() == null) {
			throw new BusinessRuleException("Address cannot be null.");
		}
	
		userRepository.save(userFilled);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByLogin(username);
	}
}
