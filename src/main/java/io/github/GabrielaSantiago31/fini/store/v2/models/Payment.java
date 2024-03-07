package io.github.GabrielaSantiago31.fini.store.v2.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.github.GabrielaSantiago31.fini.store.v2.enumeration.PaymentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_payment")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private LocalDateTime moment;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	private PaymentStatus status;
	
	@Column(nullable = false)
	private BigDecimal price; 
	
	@OneToOne
	@MapsId
	private Order order;

}
