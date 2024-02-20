package io.github.GabrielaSantiago31.fini.store.v2.models.dto.request;

import org.hibernate.validator.constraints.br.CPF;

import io.github.GabrielaSantiago31.fini.store.v2.enumeration.UserRole;
import io.github.GabrielaSantiago31.fini.store.v2.models.Address;
import io.github.GabrielaSantiago31.fini.store.v2.validation.PhoneValidator;
import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequestDto {

	@NotEmpty(message = "First name is required.")
	private String firstName;
	
	@NotEmpty(message = "Last name is required.")
	private String lastName;
	
	@Email(message = "Type a valid email address")
	private String email;
	
	@NotEmpty
	@PhoneValidator
	private String phone1;
	
	@PhoneValidator
	private String phone2;
	
	@NotEmpty(message = "CPF is required.")
	@CPF
	@Pattern(regexp = "[0-9]+")
	private String cpf;
	
	@Pattern(regexp = "[0-9]+")
	@NotEmpty
	private String dateOfBirth;
	
	@NonNull
	private Address address;
	
	@NonNull
	private UserRole role;
	
	@NotEmpty
	@Email
	private String login;
	
	@NotEmpty
	private String password;
	
}
