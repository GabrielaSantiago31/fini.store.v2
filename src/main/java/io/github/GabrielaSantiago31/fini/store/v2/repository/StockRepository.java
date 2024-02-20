package io.github.GabrielaSantiago31.fini.store.v2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.GabrielaSantiago31.fini.store.v2.models.Product;
import io.github.GabrielaSantiago31.fini.store.v2.models.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
	
	 Product findByProduct(Product product);
}
