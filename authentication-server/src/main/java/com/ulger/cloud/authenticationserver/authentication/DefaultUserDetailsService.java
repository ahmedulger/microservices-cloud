package com.ulger.cloud.authenticationserver.authentication;

import com.ulger.usermanager.api.User;
import com.ulger.usermanager.api.data.UserDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class DefaultUserDetailsService implements UserDetailsService {

    private UserDao userDao;

    public DefaultUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("Username can not be empty");
        }

        User user = userDao
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with name: " + username));

        return new DefaultUserDetails(user, getAuthorities(user));
    }

    private List<GrantedAuthority> getAuthorities(User user) {
        return AuthorityUtils.createAuthorityList("STANDARD");
    }
}