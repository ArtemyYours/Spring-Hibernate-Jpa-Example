package com.artkop.configuration.RabbitMq;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq")
@Getter
@Setter
public class RabbitMQSettings {
    private String host;
    private Integer port;
    private String httpHost;
    private String httpProtocol;
    private String httpPort;
    private String username;
    private String password;
    private String queue;
    private String exchange;
    private String routingKey;

    @Configuration
    @AllArgsConstructor
    public static class RabbitMQConfig {
        private final ObjectMapper objectMapper;
        private final RabbitMQSettings settings;


        @Bean
        public RabbitListenerContainerFactory rabbitListenerContainerFactory(
                ConnectionFactory connectionFactory,
                Jackson2JsonMessageConverter jackson2JsonMessageConverter
        )
        {
            SimpleRabbitListenerContainerFactory f = new SimpleRabbitListenerContainerFactory();

            f.setConnectionFactory(connectionFactory);
            f.setMessageConverter(jackson2JsonMessageConverter);
            f.setDefaultRequeueRejected(true);

            f.setConcurrentConsumers(1);
            f.setPrefetchCount(1);
            f.setMaxConcurrentConsumers(1);
            f.setPrefetchCount(1);
            f.setAcknowledgeMode(AcknowledgeMode.AUTO);

            return f;
        }

        //настраиваем соединение с RabbitMQ
        @Bean
        public ConnectionFactory connectionFactory() {
            CachingConnectionFactory connectionFactory =
                    new CachingConnectionFactory(settings.getHost());
            connectionFactory.setUsername(Objects.requireNonNull(settings.getUsername()));
            connectionFactory.setPassword(Objects.requireNonNull(settings.getPassword()));
            connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.SIMPLE);
            return connectionFactory;
        }

        @Bean
        public AmqpAdmin amqpAdmin() {
            return new RabbitAdmin(connectionFactory());
        }

        @Bean
        public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter producerJackson2MessageConverter) {
            RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
            rabbitTemplate.setMessageConverter(producerJackson2MessageConverter);
            rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
                if (!ack) {
                    throw new RuntimeException(cause);
                }
            });

            return rabbitTemplate;
        }

        @Bean
        public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
            return new Jackson2JsonMessageConverter(objectMapper);
        }

        @Bean
        public TopicExchange exchange() {
            return new TopicExchange(settings.getExchange(), true, false);
        }

        @Bean
        public Queue queue() {
            Queue queue = new Queue(settings.getQueue(), true);
            return queue;
        }

        @Bean
        public Binding binding1() {
            return BindingBuilder.bind(queue()).to(exchange()).with(settings.getRoutingKey());
        }




    }
}
