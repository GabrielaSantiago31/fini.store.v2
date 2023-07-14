package com.store.fini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.fini.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
