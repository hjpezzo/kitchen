package br.com.harley.kitchen.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.harley.kitchen.entity.KitchenItemMessage;
import br.com.harley.kitchen.enums.Status;

public interface OrderRepository extends MongoRepository<KitchenItemMessage, String> {

	KitchenItemMessage findByOrderId(String orderId);

	List<KitchenItemMessage> findAllByStatus(Status status);

}
