package io.github.GabrielaSantiago31.fini.store.v2.models.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequestDto {
	
	@NotEmpty(message = "required")
	private String code;
	
	@NotEmpty(message = "required")
	private String name;
	
	@NotEmpty(message = "required")
	private String flavor;
	
	@NotEmpty(message = "required")
	private String category;
	
	private String description;
	
	private String imgUrl;
	
	@NotNull(message = "required")
	private BigDecimal price;
	
	@NotNull(message = "required")
	private Double gramsPerPackage;
	
}
