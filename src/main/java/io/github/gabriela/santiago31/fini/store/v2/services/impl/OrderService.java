package io.github.gabriela.santiago31.fini.store.v2.services.impl;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.github.gabriela.santiago31.fini.store.v2.enumeration.OrderStatus;
import io.github.gabriela.santiago31.fini.store.v2.enumeration.PaymentStatus;
import io.github.gabriela.santiago31.fini.store.v2.enumeration.ProductStatus;
import io.github.gabriela.santiago31.fini.store.v2.exceptions.BusinessRuleException;
import io.github.gabriela.santiago31.fini.store.v2.models.Order;
import io.github.gabriela.santiago31.fini.store.v2.models.OrderItem;
import io.github.gabriela.santiago31.fini.store.v2.models.Product;
import io.github.gabriela.santiago31.fini.store.v2.models.Stock;
import io.github.gabriela.santiago31.fini.store.v2.models.User;
import io.github.gabriela.santiago31.fini.store.v2.models.dto.request.OrderRequestDto;
import io.github.gabriela.santiago31.fini.store.v2.models.dto.request.StockRequestDto;
import io.github.gabriela.santiago31.fini.store.v2.models.dto.response.OrderResponseDto;
import io.github.gabriela.santiago31.fini.store.v2.repository.OrderItemRepository;
import io.github.gabriela.santiago31.fini.store.v2.repository.OrderRepository;
import io.github.gabriela.santiago31.fini.store.v2.repository.PaymentRepository;
import io.github.gabriela.santiago31.fini.store.v2.repository.ProductRepository;
import io.github.gabriela.santiago31.fini.store.v2.repository.UserRepository;
import io.github.gabriela.santiago31.fini.store.v2.services.IOrder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrder{
	
	private final OrderRepository orderRepository;
	private final OrderItemRepository orderItemRepository;
	private final UserRepository userRepository;
	private final ProductRepository productRepository;
	private final StockService stockService;
	private final PaymentRepository paymentRepository;
	private final ModelMapper mapper;
	
	public OrderResponseDto findById(Long id) {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found."));
		
		OrderResponseDto responseDto = mapper.map(order, OrderResponseDto.class);
		
		return responseDto;
	}
	
	public OrderResponseDto save(OrderRequestDto orderDto) {
		
		User user = userRepository.findById(orderDto.getUser().getId())
								  .orElseThrow(() -> new BusinessRuleException("Invalid user id."));
		
		Order order = mapper.map(orderDto, Order.class);
		
		order.setMoment(LocalDateTime.now());
		order.setStatus(OrderStatus.PAYMENT);
		order.setTotal(calculateTotal(order));
		order.setUser(user);
		
		List<OrderItem> orderItems = createOrderItems(order);
		
		Order savedOrder = orderRepository.save(order);
		orderItemRepository.saveAll(orderItems);
		
		stockService.withdraw(order);
		
		OrderResponseDto responseDto = mapper.map(savedOrder, OrderResponseDto.class);
		
		return responseDto;
	}
	
	public void cancelOrder(Long id) {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
		
		for(OrderItem item : order.getOrderItems()) {
			
			Stock stock = mapper.map(stockService.findById(item.getProduct().getId()), Stock.class);
			
			stock.setQuantity(stock.getQuantity()+item.getQuantity());
			
			StockRequestDto request = mapper.map(stock, StockRequestDto.class);
			
			stockService.updateProductQuantity(request);
		}
		
		order.setStatus(OrderStatus.CANCELED);
		order.getPayment().setStatus(PaymentStatus.CANCELED);
		paymentRepository.save(order.getPayment());
		
		//chamaria um método para efetuar o estorno se estivessemos trabalhando 
		//com um sistema de pagamentos completo.
		
	}
	
	private BigDecimal calculateTotal(Order order) {
		
		BigDecimal total = BigDecimal.ZERO;
		
	    for (OrderItem orderItem : order.getOrderItems()) {
	        Product product = productRepository
	        		.findById(orderItem.getProduct().getId())
	        		.orElseThrow(() -> new BusinessRuleException("Invalid product id."));

	        if (product != null && product.getPrice() != null && product.getStatus() == ProductStatus.AVAILABLE) {

	        	BigDecimal productTotal = product.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()));
	        	total = total.add(productTotal);
	        }
	    }
	    return total;
	}
	
	private List<OrderItem> createOrderItems(Order order) {
		
        List<OrderItem> orderItems = new ArrayList<>();
        
        for (OrderItem orderItem : order.getOrderItems()) {
        	
        	stockService.isEnoughProductInStock(orderItem);
        	
            orderItem.setOrder(order); 
            
            orderItems.add(orderItem);
        }
        
        return orderItems;
    }
	
}
