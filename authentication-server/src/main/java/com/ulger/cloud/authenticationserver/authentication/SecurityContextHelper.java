package com.ulger.cloud.authenticationserver.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SecurityContextHelper {

    public static Optional<DefaultUserDetails> getUserDetails() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .filter(DefaultUserDetails.class::isInstance)
                .map(DefaultUserDetails.class::cast);
    }
}