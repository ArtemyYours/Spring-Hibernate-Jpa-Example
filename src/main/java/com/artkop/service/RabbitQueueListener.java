package com.artkop.service;

import com.artkop.model.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class RabbitQueueListener {


    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void processRequestQueue(Message response) {
        log.info(response.getMessage());
    }

}
