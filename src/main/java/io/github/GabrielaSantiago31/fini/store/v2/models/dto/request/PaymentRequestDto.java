package io.github.GabrielaSantiago31.fini.store.v2.models.dto.request;

import java.math.BigDecimal;

import io.github.GabrielaSantiago31.fini.store.v2.models.Order;
import io.micrometer.common.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentRequestDto {
	
	@NonNull
	private Order order;
	@NonNull
	private BigDecimal price;

}
