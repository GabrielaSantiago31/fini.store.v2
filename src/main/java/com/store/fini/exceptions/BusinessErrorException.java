package com.store.fini.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter @Setter
public class BusinessErrorException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private HttpStatus httpStatus = null;
	private String error = null;
	
}
