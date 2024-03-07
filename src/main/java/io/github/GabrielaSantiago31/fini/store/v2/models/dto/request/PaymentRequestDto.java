package io.github.GabrielaSantiago31.fini.store.v2.models.dto.request;

import java.math.BigDecimal;

import io.github.GabrielaSantiago31.fini.store.v2.models.Order;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentRequestDto {
	
	@NotNull(message = "required")
	private Order order;
	
	@NotNull(message = "required")
	private BigDecimal price;

}
