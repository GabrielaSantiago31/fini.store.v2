package io.github.GabrielaSantiago31.fini.store.v2.models.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressRequestDto {
	
	@NotEmpty
	private String street;
	
	@NotNull
	private Integer number;
	
	private String complement;
	
	@NotEmpty
	private String neighborhood;
	
	@NotEmpty
	private String county;
	
	@NotEmpty
	private String state;
	
	@NotEmpty
	private String country;
	
	@NotEmpty
	@Pattern(regexp = "[0-9]+")
	@Size(max = 8, message = "maximum of 8 characters")
	private String zipCode;
	
	//This constructor is necessary because ViaCep api requires these parameters
	public AddressRequestDto(Integer number, String complement, String country, String zipCode) {
		this.number = number;
		this.complement = complement;
		this.country = country;
		this.zipCode = zipCode;
	}
}
