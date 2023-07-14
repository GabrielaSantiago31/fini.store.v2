package com.store.fini.entities.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorDto {
	
	private String erro;
	private List<String> validacoes;
	
}
