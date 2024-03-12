package io.github.gabriela.santiago31.fini.store.v2.services.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.google.gson.Gson;

import io.github.gabriela.santiago31.fini.store.v2.exceptions.BusinessRuleException;
import io.github.gabriela.santiago31.fini.store.v2.models.Address;
import io.github.gabriela.santiago31.fini.store.v2.models.User;
import io.github.gabriela.santiago31.fini.store.v2.models.dto.request.UserRequestDto;
import io.github.gabriela.santiago31.fini.store.v2.models.dto.response.AddressResponseDto;
import io.github.gabriela.santiago31.fini.store.v2.repository.AddressRepository;
import io.github.gabriela.santiago31.fini.store.v2.services.IAddress;


@Service
public class AddressService implements IAddress{

	private AddressRepository addressRepository;
	private ModelMapper mapper;

	public AddressService(AddressRepository addressRepository, ModelMapper mapper) {
		this.addressRepository = addressRepository;
		this.mapper = mapper;
	}

	public Address findById(Long id) {
		return addressRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found."));
	}

	public List<Address> findAll(){
		return addressRepository.findAll();
	}
	
	public Address findByZipCode(String zipCode) {
		return addressRepository.findByZipCode(zipCode)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found"));
	}
	
	public List<Address> findByState(String state){
		return addressRepository.findByState(state);		
	}
	
	public List<Address> findByCounty(String county){
		return addressRepository.findByCounty(county);		
	}

	public User fillAddressFields(UserRequestDto userDto){
		URL url;
		try {
			url = new URL("https://viacep.com.br/ws/"+userDto.getAddress().getZipCode()+"/json/");
			URLConnection conn = url.openConnection();
			InputStream is = conn.getInputStream();   //dados da requisição que eu passei
			BufferedReader bReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			
			String line = "";
			StringBuilder jsonCep = new StringBuilder();
			
			while((line = bReader.readLine()) != null) {
				jsonCep.append(line);
			}
			
			AddressResponseDto address = new Gson().fromJson(jsonCep.toString(), AddressResponseDto.class);
			
			if(address == null) {
				throw new BusinessRuleException("Invalid Zip Code.");
			}
			
			Address userAddress = userDto.getAddress();
			
			userAddress.setStreet(address.getLogradouro());
			userAddress.setCounty(address.getLocalidade());
			userAddress.setNeighborhood(address.getBairro());
			userAddress.setState(address.getUf());
			
			User user = mapper.map(userDto, User.class);
			
			return user;
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return null;
	}
	
}
