package io.github.GabrielaSantiago31.fini.store.v2.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.github.GabrielaSantiago31.fini.store.v2.models.Product;
import io.github.GabrielaSantiago31.fini.store.v2.models.dto.request.ProductRequestDto;
import io.github.GabrielaSantiago31.fini.store.v2.models.dto.response.ProductResponseDto;
import io.github.GabrielaSantiago31.fini.store.v2.models.dto.response.UserResponseDto;
import io.github.GabrielaSantiago31.fini.store.v2.repository.ProductRepository;
import io.github.GabrielaSantiago31.fini.store.v2.services.IProduct;

@Service
public class ProductService implements IProduct{
	
	private ProductRepository productRepository;
	private ModelMapper mapper;
	
	public ProductService(ProductRepository productRepository, ModelMapper mapper) {
		this.productRepository = productRepository;
		this.mapper = mapper;
	}
	
	public List<ProductResponseDto> findAll(){
		
		List<ProductResponseDto> products = mapper.map(productRepository.findAll(), new TypeToken<List<UserResponseDto>>() {}.getType());
		
		return products
				.stream()
				.filter(product -> "AVAILABLE".equals(product.getStatus()))
				.collect(Collectors.toList());
	}
	
	public ProductResponseDto findById(Long id) {
		
		Product product = productRepository.findById(id)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));
		
		ProductResponseDto responseDto = mapper.map(product, ProductResponseDto.class);
		
		return responseDto;
	}
	
	public ProductResponseDto save(ProductRequestDto productDto) {
		
		Optional<Product> existingProduct = productRepository.findByCode(productDto.getCode());
		
		if(existingProduct.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This product has been registered.");
		}
		
		Product product = mapper.map(productDto, Product.class);
		productRepository.save(product);
		
		ProductResponseDto responseDto = mapper.map(product, ProductResponseDto.class);
		
		return responseDto;
	}
	
	public void update(Long id, ProductRequestDto productDto) {
		
		Product product = mapper.map(productDto, Product.class);
		
		productRepository
			.findById(id)
			.map(p -> {
				product.setId(p.getId());
				productRepository.save(product);
				return product;
			}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));
	}
}
