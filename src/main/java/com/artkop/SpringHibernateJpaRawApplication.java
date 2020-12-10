package com.artkop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing

public class SpringHibernateJpaRawApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringHibernateJpaRawApplication.class, args);
    }

}
