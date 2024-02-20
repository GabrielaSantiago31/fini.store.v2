package io.github.GabrielaSantiago31.fini.store.v2.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import io.github.GabrielaSantiago31.fini.store.v2.models.dto.request.OrderRequestDto;
import io.github.GabrielaSantiago31.fini.store.v2.models.dto.response.OrderResponseDto;
import io.github.GabrielaSantiago31.fini.store.v2.services.IOrder;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/finistore/order")
public class OrderController {
	
	private IOrder orderService;
	
	public OrderController(IOrder orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping("{id}")
	public OrderResponseDto findById(@PathVariable Long id) {
		return orderService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long save(@RequestBody @Valid OrderRequestDto orderDto) {
		OrderResponseDto order = orderService.save(orderDto);
		return order.getId();
	}
	
	@GetMapping("/cancel/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelOrder(@PathVariable Long id) {
		orderService.cancelOrder(id);
	}
	
}
