package io.github.GabrielaSantiago31.fini.store.v2.services;

import io.github.GabrielaSantiago31.fini.store.v2.models.dto.request.PaymentRequestDto;
import io.github.GabrielaSantiago31.fini.store.v2.models.dto.response.PaymentResponseDto;

public interface IPayment {
	
	PaymentResponseDto findById(Long id);
	PaymentResponseDto savePayment(PaymentRequestDto paymentDto);
}
