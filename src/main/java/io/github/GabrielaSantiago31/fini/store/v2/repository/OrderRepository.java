package io.github.GabrielaSantiago31.fini.store.v2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.GabrielaSantiago31.fini.store.v2.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
//	
//	List<Order> findByDate();
//	List<Order> findByUserId(Long id);
}
