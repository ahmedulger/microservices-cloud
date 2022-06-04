package com.ulger.cloud.authenticationserver.configuration;

import com.ulger.cloud.authenticationserver.api.user.data.DefaultUserDao;
import com.ulger.cloud.authenticationserver.api.user.data.UserRepository;
import com.ulger.usermanager.api.CredentialEncoder;
import com.ulger.usermanager.api.DefaultUserManager;
import com.ulger.usermanager.api.UserManager;
import com.ulger.usermanager.api.data.UserDao;
import com.ulger.usermanager.api.validation.ApacheEmailValidator;
import com.ulger.usermanager.api.validation.DefaultUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ServiceConfiguration {

    @Bean
    @Autowired
    public UserDao userDao(UserRepository userRepository) {
        return new DefaultUserDao(userRepository);
    }

    @Bean
    @Autowired
    public UserManager userManager(UserDao userDao, CredentialEncoder credentialEncoder) {
        return new DefaultUserManager(userDao, new DefaultUserValidator(new ApacheEmailValidator()), credentialEncoder);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}