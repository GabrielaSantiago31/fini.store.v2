package com.store.fini.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "db_address")
public class Address {
	
	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter @Setter
	private String street;
	@Getter @Setter
	private String number;
	@Getter @Setter
	private String city;
	@Getter @Setter
	private String state;
	@Getter @Setter
	private String zipCode;
	
	@JsonIgnore
	@OneToMany(mappedBy = "address", cascade=CascadeType.ALL , orphanRemoval = true)
	private List<User> users = new ArrayList<>();
	
	
}
