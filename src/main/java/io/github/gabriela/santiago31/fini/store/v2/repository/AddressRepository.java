package io.github.gabriela.santiago31.fini.store.v2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.gabriela.santiago31.fini.store.v2.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{
	
	Optional<Address> findByZipCode(String zipCode);
	List<Address> findByState(String state);
	List<Address> findByCounty(String county);
	List<Address> findByCountry(String country);
	

}
