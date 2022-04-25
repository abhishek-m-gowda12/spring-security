package com.abhishek.springsecurity.service.impl;

import com.abhishek.springsecurity.dto.User;
import com.abhishek.springsecurity.entity.UserDetailsEntity;
import com.abhishek.springsecurity.repository.UserDetailsRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final UserDetailsRepository userDetailsRepository;

    public JwtUserDetailsService(UserDetailsRepository userDetailsRepository) {

        this.userDetailsRepository = userDetailsRepository;
    }
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserDetailsEntity> userDetails = userDetailsRepository.findByName(username);

        return new User(userDetails.get().getName(), userDetails.get().getPassword(), userDetails.get().getId(), new ArrayList<>());

    }

}
