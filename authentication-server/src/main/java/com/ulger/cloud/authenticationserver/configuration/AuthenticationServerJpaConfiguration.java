package com.ulger.cloud.authenticationserver.configuration;

import com.ulger.cloud.authenticationserver.api.user.data.UserEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackageClasses = UserEntity.class
)
public class AuthenticationServerJpaConfiguration {

}