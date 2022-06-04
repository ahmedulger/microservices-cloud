package com.ulger.cloud.authenticationserver.api.user;

import com.ulger.usermanager.api.CredentialEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Encodes given raw password by using {@link BCryptPasswordEncoder}
 */
@Component
public class BCryptCredentialHashEncoder implements CredentialEncoder {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public BCryptCredentialHashEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public String encode(String raw) {
        return bCryptPasswordEncoder.encode(raw);
    }
}