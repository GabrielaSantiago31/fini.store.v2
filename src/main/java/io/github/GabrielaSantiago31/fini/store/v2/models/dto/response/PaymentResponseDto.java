package io.github.GabrielaSantiago31.fini.store.v2.models.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.github.GabrielaSantiago31.fini.store.v2.enumeration.PaymentStatus;
import io.github.GabrielaSantiago31.fini.store.v2.models.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentResponseDto {
	
	private Long id;
	
	private LocalDateTime moment;

	private PaymentStatus status;
	
	private BigDecimal price; 
	
	private Order order;
}
