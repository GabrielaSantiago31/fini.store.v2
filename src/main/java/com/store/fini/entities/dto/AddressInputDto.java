package com.store.fini.entities.dto;

import java.util.ArrayList;
import java.util.List;

import com.store.fini.entities.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressInputDto {
	
	@NotEmpty
	private String street;
	
	@Pattern(regexp = "^[0-9]")
	@NotEmpty
	private String number;
	
	@NotEmpty
	private String city;
	@NotEmpty
	private String state;
	
	@Pattern(regexp = "^[0-9]")
	@NotEmpty
	private String zipCode;
	
	private List<User> users = new ArrayList<>();
}
