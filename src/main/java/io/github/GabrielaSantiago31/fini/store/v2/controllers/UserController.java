package io.github.GabrielaSantiago31.fini.store.v2.controllers;

import java.util.List;

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

import io.github.GabrielaSantiago31.fini.store.v2.models.dto.request.UserRequestDto;
import io.github.GabrielaSantiago31.fini.store.v2.models.dto.response.UserResponseDto;
import io.github.GabrielaSantiago31.fini.store.v2.services.impl.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/finistore/user")
public class UserController {
	
	private UserService userService;
	
	private UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("{id}")
	public UserResponseDto findById(@PathVariable Long id) {
		return userService.findById(id);
	}
	
	@GetMapping
	public List<UserResponseDto> findAll(){
		return userService.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponseDto save(@RequestBody @Valid UserRequestDto userDto) {
		return userService.save(userDto);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Long id, @RequestBody @Valid UserRequestDto userDto) {
		userService.update(id, userDto);
	}
}
