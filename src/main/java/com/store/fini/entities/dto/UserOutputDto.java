package com.store.fini.entities.dto;

import java.util.List;

import com.store.fini.entities.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserOutputDto {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String phone;
	private String email; 
	
	private Address address;
}
