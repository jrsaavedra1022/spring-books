package com.asgeek.books.domain.service;

import com.asgeek.books.domain.auth.UserAuth;
import com.asgeek.books.domain.repository.UserAuthRepositoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthService {

    @Autowired
    private UserAuthRepositoryDTO userAuthRepositoryDTO;

    public Optional<UserAuth> getUserByEmail(String email){
        return userAuthRepositoryDTO.getUserByEmail(email);
    }

    public Optional<UserAuth> getUserByUsername(String username){
        return userAuthRepositoryDTO.getUserByUsername(username);
    }

    public UserAuth save(UserAuth userAuth){
        return userAuthRepositoryDTO.save(userAuth);
    }
}
