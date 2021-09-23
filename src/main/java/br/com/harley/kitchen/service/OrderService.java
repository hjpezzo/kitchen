package br.com.harley.kitchen.service;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import br.com.harley.kitchen.amqp.RabbitConfig;
import br.com.harley.kitchen.entity.KitchenItemMessage;
import br.com.harley.kitchen.enums.Status;
import br.com.harley.kitchen.exception.OrderAlreadyDoneException;
import br.com.harley.kitchen.exception.ResourceNotFoundException;
import br.com.harley.kitchen.repository.OrderRepository;

@Service
public class OrderService {

	OrderRepository orderRepository;
    RabbitTemplate rabbitTemplate;
	
	public OrderService(OrderRepository orderRepository, RabbitTemplate rabbitTemplate) {
		this.orderRepository = orderRepository;
	    this.rabbitTemplate = rabbitTemplate;
	}

	public KitchenItemMessage addOrder(KitchenItemMessage order) {
		return orderRepository.save(order);
	}
	
	public String finishOrder(String orderId) {
		KitchenItemMessage order = orderRepository.findByOrderId(orderId);
		
		if (order == null) {
			throw new ResourceNotFoundException();
		}
		
		if (order.getStatus().equals(Status.DONE)) {
			throw new OrderAlreadyDoneException();
		}
		
		order.setStatus(Status.DONE);
		orderRepository.save(order);

        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitConfig.ROUTING_KEY_KITCHEN_TO_ORDER, order);

		return orderId;
	}
	
	public List<KitchenItemMessage> getOrders() {
		return orderRepository.findAllByStatus(Status.PREPARING);
	}
}
