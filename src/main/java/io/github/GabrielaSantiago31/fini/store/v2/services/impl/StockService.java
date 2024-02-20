package io.github.GabrielaSantiago31.fini.store.v2.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.github.GabrielaSantiago31.fini.store.v2.enumeration.ProductStatus;
import io.github.GabrielaSantiago31.fini.store.v2.exceptions.BusinessRuleException;
import io.github.GabrielaSantiago31.fini.store.v2.models.Order;
import io.github.GabrielaSantiago31.fini.store.v2.models.OrderItem;
import io.github.GabrielaSantiago31.fini.store.v2.models.Product;
import io.github.GabrielaSantiago31.fini.store.v2.models.Stock;
import io.github.GabrielaSantiago31.fini.store.v2.models.dto.request.StockRequestDto;
import io.github.GabrielaSantiago31.fini.store.v2.models.dto.response.StockResponseDto;
import io.github.GabrielaSantiago31.fini.store.v2.models.dto.response.UserResponseDto;
import io.github.GabrielaSantiago31.fini.store.v2.repository.ProductRepository;
import io.github.GabrielaSantiago31.fini.store.v2.repository.StockRepository;
import io.github.GabrielaSantiago31.fini.store.v2.services.IStock;

@Service
public class StockService implements IStock{
	
	private StockRepository stockRepository;
	private ProductRepository productRepository;
	private ModelMapper mapper;
	
	public StockService(StockRepository stockRepository,ProductRepository productRepository, ModelMapper mapper) {
		this.stockRepository = stockRepository;
		this.productRepository = productRepository;
		this.mapper = mapper;
	}
	
	public StockResponseDto save(StockRequestDto stockDto) {
		
		Optional<Product> existingProduct = productRepository.findById(stockDto.getProduct().getId());
		
		if(existingProduct.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This product does not exist.");
		}
		
		Optional<Stock> existingStock = stockRepository.findById(stockDto.getProduct().getId());
		
		if(existingStock.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This product has been registered");
		}
		
		stockDto.setProduct(existingProduct.get());
		
		Stock stock = mapper.map(stockDto, Stock.class);
		stockRepository.save(stock);
		
		StockResponseDto responseDto = mapper.map(stock, StockResponseDto.class);

		return responseDto;
		
	}
	
	public List<StockResponseDto> findAll(){
		
		List<StockResponseDto> responseDtos = mapper.map(stockRepository.findAll(), new TypeToken<List<UserResponseDto>>() {}.getType());
		
		return responseDtos;
	}
	
	public StockResponseDto findById(Long id) {
		Stock stock = stockRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found"));
		StockResponseDto responseDto = mapper.map(stock, StockResponseDto.class);
		
		return responseDto;
	}
	
	public void updateProductQuantity(StockRequestDto stockDto) {
		
		Stock stock = mapper.map(stockDto, Stock.class);
		
		Stock oldStock = stockRepository
				.findById(stock.getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "id not found."));
		
		oldStock.setQuantity(stock.getQuantity());
		stockRepository.save(oldStock);
	}
	
	public void withdraw(Order order) {
		for(OrderItem item : order.getOrderItems()) {
			
			Stock stock = stockRepository.findById(item.getProduct().getId()).get();
			
			if(item.getProduct().getStatus() == ProductStatus.UNAVAILABLE) {
				throw new BusinessRuleException("Product " + item.getProduct().getId() + " unavailable.");
			}
			
			stock.setQuantity(stock.getQuantity()-item.getQuantity());
			
			stockRepository.save(stock);
			
			if(stock.getQuantity() == 0) {
				stock.getProduct().setStatus(ProductStatus.UNAVAILABLE);
				productRepository.save(stock.getProduct());
			}
		}
	}
	
	//Verify if the quantity of products required by the customer is available
	public void isEnoughProductInStock(OrderItem orderItem) {

		Stock productInStock = stockRepository.findById(orderItem.getProduct().getId()).get();

		if(orderItem.getQuantity() > productInStock.getQuantity()) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
					"Sorry, there are: " + productInStock.getQuantity() + " in stock");	
		}
	}

	

	
}
