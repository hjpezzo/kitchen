package br.com.harley.kitchen.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.harley.kitchen.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "orders")
@Getter
@Setter
public class KitchenItemMessage {

	@Id
	@JsonIgnore
	private String id;
	
	private String orderId;
	private List<KitchenItem> kitchenItens;
	private Status status;
	
}
