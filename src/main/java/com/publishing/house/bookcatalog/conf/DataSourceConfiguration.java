package com.publishing.house.bookcatalog.conf;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean
    @Primary
    public DataSourceProperties dataSourceProperties() {
        DataSourceProperties dataSourceProperties = new DataSourceProperties();
        dataSourceProperties.setUsername("postgres");
        dataSourceProperties.setPassword("root");
        dataSourceProperties.setUrl("jdbc:postgresql://localhost:5432/books-catalog");
        dataSourceProperties.setDriverClassName("org.postgresql.Driver");
        return dataSourceProperties;
    }

    @Bean
    public HikariDataSource dataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

}
