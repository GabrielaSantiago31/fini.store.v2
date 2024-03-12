package io.github.gabriela.santiago31.fini.store.v2.services;

import java.util.List;

import io.github.gabriela.santiago31.fini.store.v2.models.dto.request.StockRequestDto;
import io.github.gabriela.santiago31.fini.store.v2.models.dto.response.StockResponseDto;

public interface IStock {
	
	StockResponseDto save(StockRequestDto stockDto);
	List<StockResponseDto> findAll();
	StockResponseDto findById(Long id);
	void updateProductQuantity(StockRequestDto stockDto);
}
