package io.github.GabrielaSantiago31.fini.store.v2.mail;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmailRequest {
	
	@NotBlank
	private String ownerRef;
	@NotBlank
	@Email
	private String mailFrom;
	@NotBlank
	@Email
	private String mailTo;
	@NotBlank
	private String subject;
	@NotBlank
	private String text;
}
