package io.github.GabrielaSantiago31.fini.store.v2.models.dto.request;

import java.math.BigDecimal;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequestDto {
	
	private Long id;
	
	@NotEmpty
	private String code;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String flavor;
	
	@NotEmpty
	private String category;
	
	private String description;
	
	private String imgUrl;
	
	@NonNull
	private BigDecimal price;
	
	@NonNull
	private Double gramsPerPackage;
	
}
