package com.asgeek.books.domain.service;

import com.asgeek.books.domain.repository.UserAuthRepositoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserSecurityDetailService implements UserDetailsService {
    private final UserAuthRepositoryDTO userAuthRepositoryDTO;

    @Autowired
    private Environment environment;


    public UserSecurityDetailService(@Autowired UserAuthRepositoryDTO userAuthRepositoryDTO){
        this.userAuthRepositoryDTO = userAuthRepositoryDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("jrsaavedra", "{noop}Admin123*", new ArrayList<>());
    }
}
