package io.github.GabrielaSantiago31.fini.store.v2.services;

import java.util.List;

import io.github.GabrielaSantiago31.fini.store.v2.models.dto.request.ProductRequestDto;
import io.github.GabrielaSantiago31.fini.store.v2.models.dto.response.ProductResponseDto;

public interface IProduct {
	
	List<ProductResponseDto> findAll();
	ProductResponseDto findById(Long id);
	ProductResponseDto save(ProductRequestDto productDto);
	void update(Long id, ProductRequestDto productDto);
}
