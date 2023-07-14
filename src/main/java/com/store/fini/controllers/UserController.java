package com.store.fini.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.store.fini.entities.dto.AddressInputDto;
import com.store.fini.entities.dto.UserInputDto;
import com.store.fini.entities.dto.UserOutputDto;
import com.store.fini.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public List<UserOutputDto> getUsers(){
		
		return service.getUsers();
		
	}
	
	@GetMapping("{id}")
	public UserOutputDto getAnUserById(@PathVariable Long id){
		
		return service.getAnUserById(id);
		
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public UserOutputDto save(@Valid @RequestBody UserInputDto userInputDto) {
		
		return service.save(userInputDto);
	}
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping("{id}")
	public void update(@PathVariable Long id,@RequestBody UserInputDto userInputDto) {
		
		service.update(userInputDto, id);	
	}
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
		
	}
}
