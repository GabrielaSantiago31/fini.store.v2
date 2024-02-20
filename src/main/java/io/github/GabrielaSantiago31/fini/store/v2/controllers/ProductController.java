package io.github.GabrielaSantiago31.fini.store.v2.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.GabrielaSantiago31.fini.store.v2.models.dto.request.ProductRequestDto;
import io.github.GabrielaSantiago31.fini.store.v2.models.dto.response.ProductResponseDto;
import io.github.GabrielaSantiago31.fini.store.v2.services.impl.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/finistore/product")
public class ProductController {
	
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping
	public List<ProductResponseDto> findAll(){
		return productService.findAll();
	}
	
	@GetMapping("{id}")
	public ProductResponseDto findById(@PathVariable Long id) {
		return productService.findById(id);
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public ProductResponseDto save(@RequestBody @Valid ProductRequestDto product) {
		return productService.save(product);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Long id, @RequestBody @Valid ProductRequestDto product) {
		productService.update(id, product);
	}
	
}
