package io.github.gabriela.santiago31.fini.store.v2.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.github.gabriela.santiago31.fini.store.v2.enumeration.ProductStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Entity
@Table(name="tb_product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String code;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String flavor;
	
	@Column(nullable = false)
	private String category;
	
	private String description;
	
	private String imgUrl;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ProductStatus status;
	
	@Column(nullable = false)
	private BigDecimal price;
	
	@Column(nullable = false)
	private Double gramsPerPackage;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<OrderItem> orderItems = new ArrayList<>();
	
	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
	private Stock stock;
	
	public Product() {
		this.status = ProductStatus.AVAILABLE;
	}
	
}
