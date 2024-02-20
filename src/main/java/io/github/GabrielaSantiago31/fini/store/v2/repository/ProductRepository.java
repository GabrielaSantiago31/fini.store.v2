package io.github.GabrielaSantiago31.fini.store.v2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.GabrielaSantiago31.fini.store.v2.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	Optional<Product> findByCode(String code);
}
