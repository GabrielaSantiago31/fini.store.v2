package io.github.gabriela.santiago31.fini.store.v2.models.dto.request;

import io.github.gabriela.santiago31.fini.store.v2.models.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockRequestDto {
	
	@NotNull(message="required")
	private Product product;
	
	@NotNull(message="required")
	@Min(1)
	private Integer quantity;
}
