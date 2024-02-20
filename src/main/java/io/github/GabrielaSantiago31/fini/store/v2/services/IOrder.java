package io.github.GabrielaSantiago31.fini.store.v2.services;

import io.github.GabrielaSantiago31.fini.store.v2.models.dto.request.OrderRequestDto;
import io.github.GabrielaSantiago31.fini.store.v2.models.dto.response.OrderResponseDto;

public interface IOrder {
	
	OrderResponseDto findById(Long id);
	OrderResponseDto save(OrderRequestDto order);
	void cancelOrder(Long id);

}
