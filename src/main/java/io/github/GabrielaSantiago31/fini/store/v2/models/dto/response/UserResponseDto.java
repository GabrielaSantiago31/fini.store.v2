package io.github.GabrielaSantiago31.fini.store.v2.models.dto.response;

import io.github.GabrielaSantiago31.fini.store.v2.enumeration.UserRole;
import io.github.GabrielaSantiago31.fini.store.v2.models.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponseDto {
	
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String phone1;
	
	private String phone2;
	
	private String dateOfBirth;
	
	private Address address;
	
	private UserRole role;
	
	private String login;
	
}
