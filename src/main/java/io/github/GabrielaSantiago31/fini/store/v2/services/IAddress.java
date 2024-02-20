package io.github.GabrielaSantiago31.fini.store.v2.services;

import java.util.List;

import io.github.GabrielaSantiago31.fini.store.v2.models.Address;

public interface IAddress {
	
	Address findById(Long id);
	List<Address> findAll();
	Address findByZipCode(String zipCode);
	List<Address> findByState(String state);
	List<Address> findByCounty(String county);
}
