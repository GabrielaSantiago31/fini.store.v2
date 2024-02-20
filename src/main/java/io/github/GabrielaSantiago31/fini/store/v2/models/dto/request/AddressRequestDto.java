package io.github.GabrielaSantiago31.fini.store.v2.models.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressRequestDto {

	private String street;
	
	@NotNull
	private Integer number;
	
	private String complement;
	
	private String neighborhood;
	
	private String county;
	
	private String state;
	
	private String country;
	
	@NotEmpty
	@Pattern(regexp = "[0-9]+")
	private String zipCode;
	
	public AddressRequestDto(Integer number, String complement, String country, String zipCode) {
		this.number = number;
		this.complement = complement;
		this.country = country;
		this.zipCode = zipCode;
	}
}
