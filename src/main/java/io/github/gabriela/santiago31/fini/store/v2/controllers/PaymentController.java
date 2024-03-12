package io.github.gabriela.santiago31.fini.store.v2.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.gabriela.santiago31.fini.store.v2.models.dto.request.PaymentRequestDto;
import io.github.gabriela.santiago31.fini.store.v2.models.dto.response.PaymentResponseDto;
import io.github.gabriela.santiago31.fini.store.v2.services.IPayment;
import io.github.gabriela.santiago31.fini.store.v2.services.impl.PaymentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("finistore/payment")
public class PaymentController {

	private IPayment paymentService;
	
	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	@GetMapping("{id}")
	public PaymentResponseDto findById(@PathVariable Long id) {
		return paymentService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long save(@RequestBody @Valid PaymentRequestDto paymentDto) {
		PaymentResponseDto dto = paymentService.savePayment(paymentDto);
		return dto.getId();
	}
}
