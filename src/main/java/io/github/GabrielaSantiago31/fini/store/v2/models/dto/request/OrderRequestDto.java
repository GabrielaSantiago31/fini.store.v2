package io.github.GabrielaSantiago31.fini.store.v2.models.dto.request;

import java.util.ArrayList;
import java.util.List;

import io.github.GabrielaSantiago31.fini.store.v2.models.OrderItem;
import io.github.GabrielaSantiago31.fini.store.v2.models.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequestDto {
	
	@NotNull(message = "required")
	private User user;
	
	@NotEmpty(message = "required")
	private List<OrderItem> orderItems = new ArrayList<>();
	
}
