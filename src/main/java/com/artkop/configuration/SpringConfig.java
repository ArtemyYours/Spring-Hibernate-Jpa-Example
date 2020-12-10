package com.artkop.configuration;

import com.artkop.service.GrpcClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public GrpcClient getClient(){
        return new GrpcClient();
    }
}
