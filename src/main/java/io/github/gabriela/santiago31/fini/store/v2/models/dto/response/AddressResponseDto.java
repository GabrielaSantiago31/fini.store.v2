package io.github.gabriela.santiago31.fini.store.v2.models.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressResponseDto {
	
	private String logradouro;
	private String bairro;
	private String localidade;
	private String uf;
}
