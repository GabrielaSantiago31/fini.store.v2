package com.store.fini.entities.dto;

import com.store.fini.entities.Address;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInputDto {
	
	private String firstName;
	private String lastName;
	
	@Size(min=10, max=11)
	@NotEmpty
	private String phone;
	
	@NotEmpty
	private String cpf;
	
	@Email
	@NotEmpty
	private String email; 
	
	private Address address;
	
}
