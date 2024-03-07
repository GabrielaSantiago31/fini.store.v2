package io.github.GabrielaSantiago31.fini.store.v2.models.dto.request;

import io.github.GabrielaSantiago31.fini.store.v2.enumeration.UserRole;
import io.github.GabrielaSantiago31.fini.store.v2.models.Address;
import io.github.GabrielaSantiago31.fini.store.v2.validation.PhoneValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserUpdateRequestDto {

	@NotEmpty(message = "required")
	private String firstName;

	@NotEmpty(message = "required")
	private String lastName;

	@NotEmpty(message = "required")
	@Email(message = "Type a valid email address")
	private String email;

	@NotEmpty(message = "required")
	@PhoneValidator
	private String phone1;

	@PhoneValidator
	private String phone2;

	@Pattern(regexp = "[0-9]+")
	@NotEmpty(message = "required")
	@Size(min = 8, max = 8, message = "Insert 8 characters")
	private String dateOfBirth;

	@NotNull(message = "required")
	private Address address;

	@NotNull(message = "required")
	private UserRole role;

	@NotEmpty(message = "required")
	@Size(min = 8, max = 12, message = "from 8 to 12 characters")
	private String password;

}


