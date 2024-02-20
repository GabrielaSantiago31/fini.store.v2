package io.github.GabrielaSantiago31.fini.store.v2.models.dto.request;

import io.github.GabrielaSantiago31.fini.store.v2.models.Product;
import io.micrometer.common.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockRequestDto {
	
	@NonNull
	private Product product;
	
	@NonNull
	private Integer quantity;
}
