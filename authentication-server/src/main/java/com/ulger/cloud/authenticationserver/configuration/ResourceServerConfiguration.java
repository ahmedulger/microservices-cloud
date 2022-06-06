package com.ulger.cloud.authenticationserver.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private TokenStore tokenStore;
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    public ResourceServerConfiguration(TokenStore tokenStore, AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.tokenStore = tokenStore;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                    .disable()
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/register").permitAll()
                    .antMatchers(HttpMethod.GET, "/register").permitAll()
                    .antMatchers(HttpMethod.GET, "/message").access("#oauth2.hasScope('read')")
                    .antMatchers(HttpMethod.GET, "/rest/api/v1/user/**").authenticated()
                    .antMatchers(HttpMethod.POST, "/rest/api/v1/user").not().authenticated()
                    .antMatchers(HttpMethod.PUT, "/rest/api/v1/user").authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .successHandler(authenticationSuccessHandler)
                    .loginProcessingUrl("/perform_login").permitAll()
                    .and()
                .logout()
                    .disable()
                .sessionManagement()
                    .disable();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenStore(tokenStore);
    }
}