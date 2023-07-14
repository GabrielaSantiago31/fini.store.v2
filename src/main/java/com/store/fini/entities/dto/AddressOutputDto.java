package com.store.fini.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressOutputDto {
	
	private Long id;
	private String street;
	private String number;
	private String city;
	private String state;
	private String zipCode;
}
