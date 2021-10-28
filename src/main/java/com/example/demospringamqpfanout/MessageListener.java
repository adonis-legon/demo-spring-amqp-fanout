package com.example.demospringamqpfanout;

import java.net.UnknownHostException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    @Autowired
    private RabbitConfig rabbitConfig;

    @RabbitListener(queues = "#{rabbitConfig.getQueueName()}")
    public void receiveMessage(String message){
        try {
            System.out.println(String.format("Listener for queue: %s, received message: %s", rabbitConfig.getQueueName(), message));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
