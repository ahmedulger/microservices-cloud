package com.ulger.cloud.authenticationserver.authentication;

import com.ulger.cloud.authenticationserver.api.user.data.RoleEntity;
import com.ulger.cloud.authenticationserver.api.user.data.UserEntity;
import com.ulger.cloud.authenticationserver.api.user.data.UserRepository;
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

    private final UserRepository userRepository;

    public DefaultUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("Username can not be empty");
        }

        UserEntity user = userRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with name: " + username));

        return new DefaultUserDetails(user, getAuthorities(user));
    }

    private List<GrantedAuthority> getAuthorities(UserEntity user) {

        String[] authorities = user
                .getRoles()
                .stream()
                .map(RoleEntity::getName)
                .toArray(String[]::new);

        return AuthorityUtils.createAuthorityList(authorities);
    }
}