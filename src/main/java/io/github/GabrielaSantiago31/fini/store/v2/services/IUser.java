package io.github.GabrielaSantiago31.fini.store.v2.services;

import java.util.List;

import io.github.GabrielaSantiago31.fini.store.v2.models.dto.request.UserRequestDto;
import io.github.GabrielaSantiago31.fini.store.v2.models.dto.response.UserResponseDto;

public interface IUser {
	
	UserResponseDto findById(Long id);
	List<UserResponseDto> findAll();
	UserResponseDto save(UserRequestDto user);
	void delete(Long id);
	void update(Long id, UserRequestDto user);
}
