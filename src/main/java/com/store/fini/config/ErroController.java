package com.store.fini.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.store.fini.entities.dto.ErrorDto;
import com.store.fini.exceptions.BusinessErrorException;

@RestControllerAdvice
public class ErroController {
	
	@Autowired
	private MessageSource messageSource;
	
	public ResponseEntity<ErrorDto> handle(BusinessErrorException e){
		ErrorDto erroDto = new ErrorDto();
		erroDto.setErro(e.getError());
		
		return ResponseEntity.status(e.getHttpStatus()).body(erroDto);
	}
	
	public ErrorDto handle(BindException exception) {
		List<String> validacoes = new ArrayList<>();
		
		for(FieldError error : exception.getBindingResult().getFieldErrors()) {
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			validacoes.add(error.getField() + ": " + mensagem);
		}
		
		ErrorDto errorDto = new ErrorDto();
		errorDto.setErro("Erro de validação!");
		errorDto.setValidacoes(validacoes);
		
		return errorDto;
	}
}
