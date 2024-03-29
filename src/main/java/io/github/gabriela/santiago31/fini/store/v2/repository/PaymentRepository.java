package io.github.gabriela.santiago31.fini.store.v2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.gabriela.santiago31.fini.store.v2.models.Order;
import io.github.gabriela.santiago31.fini.store.v2.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
	Payment findByOrder(Order order);
}
