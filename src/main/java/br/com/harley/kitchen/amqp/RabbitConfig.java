package br.com.harley.kitchen.amqp;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RabbitConfig {

	public static String EXCHANGE_NAME = "order-process-exchange";
	public static final String ROUTING_KEY_ORDER_TO_KITCHEN = "order-to-kitchen";
	public static final String ROUTING_KEY_KITCHEN_TO_ORDER = "kitchen-to-order";
	public static final String QUEUE_TO_KITCHEN = "order-to-kitchen-queue";
	public static final String QUEUE_FROM_KITCHEN = "kitchen-to-order-queue";

	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final var rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setExchange(RabbitConfig.EXCHANGE_NAME);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
		return new Jackson2JsonMessageConverter(objectMapper);
	}

}
