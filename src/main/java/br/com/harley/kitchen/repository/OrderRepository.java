package br.com.harley.kitchen.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.harley.kitchen.entity.KitchenItemMessage;

public interface OrderRepository extends MongoRepository<KitchenItemMessage, String> {

}
