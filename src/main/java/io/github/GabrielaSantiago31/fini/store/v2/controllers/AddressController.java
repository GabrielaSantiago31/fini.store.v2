package io.github.GabrielaSantiago31.fini.store.v2.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.GabrielaSantiago31.fini.store.v2.models.Address;
import io.github.GabrielaSantiago31.fini.store.v2.services.impl.AddressService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/finistore/address")
public class AddressController {
	
	private AddressService addressService;
	
	public AddressController(AddressService addressRepository) {
		this.addressService = addressRepository;
	}
	
	@GetMapping("{id}")
	public Address findById(@PathVariable Long id) {
		return addressService.findById(id);
	}
	
	@GetMapping
	public List<Address> findAll(){
		return addressService.findAll();
	}
	
	@GetMapping("/zipcode/{zipCode}")
	public Address findByZipCode(@PathVariable @Valid String zipCode) {
		return addressService.findByZipCode(zipCode);
	}
	
	@GetMapping("/state/{state}")
	public List<Address> findByState(@PathVariable String state){
		return addressService.findByState(state);
	}
	
	@GetMapping("/county/{county}")
	public List<Address> findByCounty(@PathVariable String county){
		return addressService.findByCounty(county);
	}

}
