package com.vti.demo_crud.service;

import com.vti.demo_crud.entity.UserEntity;
import com.vti.demo_crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByEmailOrPhoneNumber(username, username);

        if (userEntity != null) {
            return User.withDefaultPasswordEncoder()
                    .username(userEntity.getEmail())
                    .password(userEntity.getPassword())
                    .roles("USER")
                    .build();
        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }

}