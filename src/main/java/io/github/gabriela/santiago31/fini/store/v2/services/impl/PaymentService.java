package io.github.gabriela.santiago31.fini.store.v2.services.impl;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.github.gabriela.santiago31.fini.store.v2.enumeration.OrderStatus;
import io.github.gabriela.santiago31.fini.store.v2.enumeration.PaymentStatus;
import io.github.gabriela.santiago31.fini.store.v2.mail.EmailService;
import io.github.gabriela.santiago31.fini.store.v2.models.Order;
import io.github.gabriela.santiago31.fini.store.v2.models.Payment;
import io.github.gabriela.santiago31.fini.store.v2.models.dto.request.PaymentRequestDto;
import io.github.gabriela.santiago31.fini.store.v2.models.dto.response.PaymentResponseDto;
import io.github.gabriela.santiago31.fini.store.v2.repository.OrderRepository;
import io.github.gabriela.santiago31.fini.store.v2.repository.PaymentRepository;
import io.github.gabriela.santiago31.fini.store.v2.services.IPayment;

@Service
public class PaymentService implements IPayment{
	
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private EmailService emailService;
	@Autowired
	private ModelMapper mapper;
	
	
	@Value("${mail.order.placed.subject}")
	private String subjectOrderPlaced;
	
	@Value("${mail.order.placed.msg}")
	private String msgOrderPlaced;
	
	public PaymentResponseDto findById(Long id) {
		Payment payment = paymentRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found."));
		
		PaymentResponseDto responseDto = mapper.map(payment, PaymentResponseDto.class);
		
		return responseDto;
	}
	
	public PaymentResponseDto savePayment(PaymentRequestDto paymentDto) {
		
		Order order = orderRepository.findById(paymentDto.getOrder().getId()).get();
		
		if(order == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
		}
		
		if(order.getStatus() == OrderStatus.ORDER_PLACED){
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This order has been paid.");
		}
		
		Payment payment = mapper.map(paymentDto, Payment.class);
		payment.setMoment(LocalDateTime.now());
		payment.setStatus(PaymentStatus.PENDING);
		payment.setOrder(order);
		
		if(order.getPayment() != null && order.getStatus() == OrderStatus.PAYMENT) {
			
			payment.setId(order.getPayment().getId());
		}

		if(payment.getPrice().compareTo(order.getTotal()) == 0) {
			
			payment.setStatus(PaymentStatus.APPROVED);
			
			order.setStatus(OrderStatus.ORDER_PLACED);
			orderRepository.save(order);
			
		}else {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The payment amount mismatches the order total. Please, try again.");
		}
		
		paymentRepository.save(payment);
		
		emailService.sendEmail(order.getUser().getEmail(),subjectOrderPlaced,msgOrderPlaced);
		
		PaymentResponseDto responseDto = mapper.map(payment, PaymentResponseDto.class);
		
		return responseDto;
	}
}
