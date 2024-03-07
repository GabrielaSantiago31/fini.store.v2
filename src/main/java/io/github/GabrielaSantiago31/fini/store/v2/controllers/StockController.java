package io.github.GabrielaSantiago31.fini.store.v2.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.GabrielaSantiago31.fini.store.v2.models.dto.request.StockRequestDto;
import io.github.GabrielaSantiago31.fini.store.v2.models.dto.response.StockResponseDto;
import io.github.GabrielaSantiago31.fini.store.v2.services.impl.StockService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/finistore/stock")
public class StockController {
	
	private StockService stockService;
	
	public StockController(StockService stockService) {
		this.stockService = stockService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public StockResponseDto save(@RequestBody @Valid StockRequestDto stockDto) {
		return stockService.save(stockDto);
	}
	
	@GetMapping
	public List<StockResponseDto> findAll() {
		return stockService.findAll();
	}
	
	@GetMapping("{id}")
	public StockResponseDto findById(@PathVariable Long id) {
		return stockService.findById(id);
	}
	
	@PatchMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateProductQuantity(@RequestBody @Valid StockRequestDto stock) {
		stockService.updateProductQuantity(stock);
	}
	
	
}
