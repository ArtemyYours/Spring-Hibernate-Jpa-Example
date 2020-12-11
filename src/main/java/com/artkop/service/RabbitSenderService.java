package com.artkop.service;

import com.artkop.configuration.RabbitMq.RabbitMQSettings;
import com.artkop.model.Message;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RabbitSenderService {
    RabbitTemplate rabbitTemplate;
    RabbitMQSettings rabbitMQSettings;

    public void sendMessageToRabbit(String text) {
        Message message = new Message();
        message.setMessage(text);
        rabbitTemplate
                .convertAndSend(rabbitMQSettings.getExchange(), rabbitMQSettings.getRoutingKey(), message);
    }
}
