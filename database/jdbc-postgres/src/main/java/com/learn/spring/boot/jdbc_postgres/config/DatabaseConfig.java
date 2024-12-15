package com.learn.spring.boot.jdbc_postgres.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

// @Configuration - tell Spring boot that it is a configuration class
@Configuration
public class DatabaseConfig {

    /*
    @Bean - annotation to explicitly define a bean with in a configuration class
    The below method receives datasource bean as an argument and return a Jdbc Template configured with that datasource
    The datasource bean is usually configured by spring via application.properties
     */
    @Bean
    public JdbcTemplate jdbcTemplate(final DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
