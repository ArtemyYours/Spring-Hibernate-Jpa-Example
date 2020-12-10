package com.artkop.configuration.settings;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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
}
