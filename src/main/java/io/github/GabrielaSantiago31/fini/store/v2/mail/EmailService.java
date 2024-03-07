package io.github.GabrielaSantiago31.fini.store.v2.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
	
	private final RestTemplate restTemplate;
	
	@Value("${ownerRef}")
	private String ownerRef;
	
	@Value("${mailFrom}")
	private String mailFrom;
	
	@Value("${mail.host}")
	private String mailHost;
	
	public void sendEmail(String mailTo, String subject, String text){
		EmailRequest request = new EmailRequest(ownerRef, mailFrom, mailTo, subject, text);
		restTemplate.postForEntity(mailHost, request, Void.class);
	}
	
}
