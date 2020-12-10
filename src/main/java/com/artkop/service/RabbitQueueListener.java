package com.artkop.service;

import com.artkop.model.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class RabbitQueueListener {

    @Autowired
    GrpcClient grpcClient;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void processRequestQueue(Message response) {
        log.info("Message is caught in RabbitMQ server");
        log.info("Message is: " + response.getMessage());
        log.info("Sending message to gRPC server...");
        grpcClient.sendMessageToGrpc(response.getMessage());
        log.info("Message sent...");
    }

}
