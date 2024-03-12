package io.github.gabriela.santiago31.fini.store.v2.models.dto.response;

import java.math.BigDecimal;

import io.github.gabriela.santiago31.fini.store.v2.enumeration.ProductStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponseDto {
	
	private Long id;
	
	private String code;
	
	private String name;
	
	private String flavor;
	
	private String category;
	
	private String description;
	
	private String imgUrl;
	
	@Enumerated(EnumType.STRING)
	private ProductStatus status;
	
	private BigDecimal price;
	
	private Double gramsPerPackage;
	
}
