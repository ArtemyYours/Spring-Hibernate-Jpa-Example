package com.artkop.configuration;


import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfig {

    @Bean
    @ConfigurationProperties(prefix = "liquibase.post-jpa-liquibase")
    public LiquibaseProperties postJpaLiquibaseProp() {
        return new LiquibaseProperties();
    }

    @Bean(name = "postJpaLiquibase")
    @Autowired
    @DependsOn("entityManagerFactory")
    public SpringLiquibase postJpaLiquibase(DataSource dataSourceApp,
                                            LiquibaseProperties properties) {
        SpringLiquibase liquibase = new SpringLiquibase();

        liquibase.setContexts(properties.getContexts());
        liquibase.setChangeLog(properties.getChangeLog());
        liquibase.setDataSource(dataSourceApp);
        liquibase.setShouldRun(properties.isEnabled());


        return liquibase;
    }
}
