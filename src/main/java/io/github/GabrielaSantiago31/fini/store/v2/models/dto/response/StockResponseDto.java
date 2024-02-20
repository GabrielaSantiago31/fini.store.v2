package io.github.GabrielaSantiago31.fini.store.v2.models.dto.response;

import io.github.GabrielaSantiago31.fini.store.v2.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StockResponseDto {
	
	private Long id;

	private Product product;
	
	private Integer quantity;

	
}
