package br.com.harley.kitchen.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.harley.kitchen.entity.KitchenItemMessage;
import br.com.harley.kitchen.service.OrderService;

@Component
public class OrderConsumer {

    OrderService orderService;

    public OrderConsumer(OrderService orderService, RabbitTemplate rabbitTemplate) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_TO_KITCHEN)
    public void consumer(@Payload KitchenItemMessage order) {
        Logger logger = LoggerFactory.getLogger(OrderConsumer.class);
        logger.info("Recebido pedido de Kitchen do pedido: ".concat(order.getOrderId()));
        orderService.addOrder(order);
    }

}
