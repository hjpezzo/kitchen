package br.com.harley.kitchen.service;

import org.springframework.stereotype.Service;

import br.com.harley.kitchen.entity.KitchenItemMessage;
import br.com.harley.kitchen.repository.OrderRepository;

@Service
public class OrderService {

	OrderRepository orderRepository;
	
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public KitchenItemMessage addOrder(KitchenItemMessage order) {
		return orderRepository.save(order);
	}
}
