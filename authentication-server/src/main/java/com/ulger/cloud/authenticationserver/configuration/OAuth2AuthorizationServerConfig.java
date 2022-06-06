package com.ulger.cloud.authenticationserver.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;
    private final TokenStore tokenStore;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenConverter accessTokenConverter;

    public OAuth2AuthorizationServerConfig(
            AuthenticationManager authenticationManager,
            TokenStore tokenStore,
            PasswordEncoder passwordEncoder,
            AccessTokenConverter accessTokenConverter) {

        this.authenticationManager = authenticationManager;
        this.tokenStore = tokenStore;
        this.passwordEncoder = passwordEncoder;
        this.accessTokenConverter = accessTokenConverter;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenStore(tokenStore)
                .accessTokenConverter(accessTokenConverter)
                .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("order-service")
                .secret(passwordEncoder.encode("pwd-order-service"))
                .scopes("read", "write")
                .authorizedGrantTypes("authorization_code", "password", "refresh_token")
                .redirectUris("http://localhost:8082/oauth/authorized")
                .autoApprove(true)
                .accessTokenValiditySeconds(200000)
                .refreshTokenValiditySeconds(200000);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }
}