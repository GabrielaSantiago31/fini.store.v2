package com.store.fini.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.store.fini.entities.Address;
import com.store.fini.entities.dto.AddressInputDto;
import com.store.fini.entities.dto.AddressOutputDto;
import com.store.fini.entities.dto.UserOutputDto;
import com.store.fini.exceptions.BusinessErrorException;
import com.store.fini.repositories.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	public List<UserOutputDto> getAddresses(){
		
		List<Address> addresses = repository.findAll();
		
		List<UserOutputDto> addressesDto = mapper.map(addresses, new TypeToken<List<AddressOutputDto>>(){}.getType());
		
		return addressesDto;
	}
	
	public AddressOutputDto getAnAddressById(Long id) {
		
		Optional<Address> opt = repository.findById(id);
		
		if(!opt.isPresent()) {
			throw new BusinessErrorException(HttpStatus.NOT_FOUND, "Address not found");
		}
		
		Address addressDb = opt.get();
		
		AddressOutputDto outputDto = mapper.map(addressDb, AddressOutputDto.class);
		
		return outputDto;
	}
	
	public AddressOutputDto saveAnAddress(AddressInputDto addressInputDto) {
		try {
			Address address = mapper.map(addressInputDto, Address.class);
			
			Address addressDb = repository.save(address);
			
			AddressOutputDto outputDto = mapper.map(addressDb, AddressOutputDto.class);
			
			return outputDto;
		}catch(DataIntegrityViolationException e) {
			throw new BusinessErrorException(HttpStatus.NOT_FOUND, "Address not found");
		}
	}
	
	public void updateAnAddress(AddressInputDto newAddress, Long id) {
		
		Optional<Address> opt = repository.findById(id);
		
		if(!opt.isPresent()) {
			throw new BusinessErrorException(HttpStatus.NOT_FOUND, "Address not found");
		}
		
		Address addressDb = opt.get();
		
		mapper.map(newAddress, addressDb);
		
		repository.save(addressDb);
	}

	public void delete(Long id) {
		
		if(!repository.existsById(id)) {
			throw new BusinessErrorException(HttpStatus.NOT_FOUND, "Address not found");
		}
		
		repository.deleteById(id);
	}
	
	
}
