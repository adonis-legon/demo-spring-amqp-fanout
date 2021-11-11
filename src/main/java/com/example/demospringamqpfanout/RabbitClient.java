package com.example.demospringamqpfanout;

import java.net.UnknownHostException;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitClient {
    @Autowired
    private RabbitConfig rabbitConfig;

    @Autowired
    RabbitAdmin rabbitAdmin;

    @PostConstruct
    private void init() throws UnknownHostException {
        FanoutExchange exchange = new FanoutExchange(rabbitConfig.getExchangeName(), false, false);
        rabbitAdmin.declareExchange(exchange);

		Queue queue = new Queue(rabbitConfig.getQueueName());
		rabbitAdmin.declareQueue(queue);

		Binding binding = BindingBuilder.bind(queue).to(exchange);
		rabbitAdmin.declareBinding(binding);
    }
}
