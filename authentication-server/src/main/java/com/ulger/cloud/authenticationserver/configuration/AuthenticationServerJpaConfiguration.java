package com.ulger.cloud.authenticationserver.configuration;

import com.ulger.cloud.authenticationserver.api.user.data.UserEntity;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackageClasses = UserEntity.class,
        entityManagerFactoryRef = "authenticationServerEntityManagerFactory",
        transactionManagerRef = "authenticationServerTransactionManager"
)
public class AuthenticationServerJpaConfiguration {

    @Primary
    @Bean("authenticationServerEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean authenticationServerEntityManagerFactory(
            @Qualifier("authenticationServerDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        
        return (LocalContainerEntityManagerFactoryBean) builder
                .withDataSource(dataSource)
                .build();
    }

    @Primary
    @Bean("authenticationServerTransactionManager")
    public PlatformTransactionManager authenticationServerTransactionManager(
            @Qualifier("authenticationServerEntityManagerFactory") LocalContainerEntityManagerFactoryBean authenticationServerEntityManagerFactory) {

        return new JpaTransactionManager(Objects.requireNonNull(authenticationServerEntityManagerFactory.getObject()));
    }
}