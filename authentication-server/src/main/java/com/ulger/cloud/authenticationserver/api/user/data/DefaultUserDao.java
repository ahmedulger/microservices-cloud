package com.ulger.cloud.authenticationserver.api.user.data;

import com.ulger.usermanager.api.User;
import com.ulger.usermanager.api.data.UserDao;

import java.util.Optional;

public class DefaultUserDao implements UserDao {

    private final UserRepository userRepository;

    public DefaultUserDao(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email).map(userEntity -> userEntity);
    }

    @Override
    public User insert(User user) {
        return null;
    }
}