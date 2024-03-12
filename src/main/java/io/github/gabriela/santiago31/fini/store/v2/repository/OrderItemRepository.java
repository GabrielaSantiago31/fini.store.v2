package io.github.gabriela.santiago31.fini.store.v2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.gabriela.santiago31.fini.store.v2.models.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
