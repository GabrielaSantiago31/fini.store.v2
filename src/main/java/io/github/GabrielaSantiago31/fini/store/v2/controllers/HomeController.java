package io.github.GabrielaSantiago31.fini.store.v2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.GabrielaSantiago31.fini.store.v2.models.User;
import io.github.GabrielaSantiago31.fini.store.v2.models.dto.request.UserRequestDto;
import io.github.GabrielaSantiago31.fini.store.v2.models.dto.response.JwtDto;
import io.github.GabrielaSantiago31.fini.store.v2.models.dto.response.UserResponseDto;
import io.github.GabrielaSantiago31.fini.store.v2.models.records.AuthenticationDto;
import io.github.GabrielaSantiago31.fini.store.v2.security.TokenProvider;
import io.github.GabrielaSantiago31.fini.store.v2.services.impl.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("finistore/home")
public class HomeController {
	
	@Autowired
	private TokenProvider tokenService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<JwtDto> Login(@RequestBody @Valid AuthenticationDto dto) {
		
		var loginPassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
		var authUser = this.authenticationManager.authenticate(loginPassword);
		var accessToken = tokenService.generateAccessToken((User) authUser.getPrincipal());
		return ResponseEntity.ok(new JwtDto(accessToken));
	}
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponseDto register(@RequestBody @Valid UserRequestDto userDto) {
		
		return userService.save(userDto);
	}
}
