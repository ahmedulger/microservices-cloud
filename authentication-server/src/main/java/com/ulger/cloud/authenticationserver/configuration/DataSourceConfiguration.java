package com.ulger.cloud.authenticationserver.configuration;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean(name = "authenticationServerDataSourceProperties")
    @ConfigurationProperties("spring.datasource.authentication-manager")
    public DataSourceProperties authenticationServerDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "authenticationServerDataSource")
    public DataSource authenticationServerDataSource() {
        return authenticationServerDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }
}