package io.github.GabrielaSantiago31.fini.store.v2.models.dto.request;

import java.util.ArrayList;
import java.util.List;

import io.github.GabrielaSantiago31.fini.store.v2.models.OrderItem;
import io.github.GabrielaSantiago31.fini.store.v2.models.User;
import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequestDto {
	
	@NonNull
	private User user;
	
	@NotEmpty
	private List<OrderItem> orderItems = new ArrayList<>();
	
}
