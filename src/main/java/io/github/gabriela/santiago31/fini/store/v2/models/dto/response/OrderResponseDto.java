package io.github.gabriela.santiago31.fini.store.v2.models.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import io.github.gabriela.santiago31.fini.store.v2.enumeration.OrderStatus;
import io.github.gabriela.santiago31.fini.store.v2.models.OrderItem;
import io.github.gabriela.santiago31.fini.store.v2.models.Payment;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderResponseDto {
	
	private Long id;
	
	private LocalDateTime moment;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	private BigDecimal total;
	
	private List<OrderItem> orderItems = new ArrayList<>();
	
	private Payment payment;
}
