package com.example.SDA_demo_animals.security.service;

import com.example.SDA_demo_animals.config.UserRoles;
import com.example.SDA_demo_animals.repositories.UserRepository;
import com.example.SDA_demo_animals.security.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private PasswordEncoder encoder;

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = new UserWrapper(userRepository.findById(username).get());
        return userDetails;
    }

    public void registerUser(User user) {
        if (userRepository.existsById(user.getUsername())) {
            return;
        }
        user.setPassword(
                encoder.encode(
                        user.getPassword()));
        user.addAuthority(UserRoles.DEFAULT_ROLE.getAuthority());
        userRepository.save(user);
    }
}
