package br.com.harley.kitchen.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "orders")
@Getter
@Setter
public class KitchenItemMessage {

	private String orderId;
	private List<KitchenItem> kitchenItens;
	
}
