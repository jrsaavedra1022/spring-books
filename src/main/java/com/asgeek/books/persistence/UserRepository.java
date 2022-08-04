package com.asgeek.books.persistence;

import com.asgeek.books.domain.auth.UserAuth;
import com.asgeek.books.domain.repository.UserAuthRepositoryDTO;
import com.asgeek.books.persistence.crud.UserCrudRepository;
import com.asgeek.books.persistence.entity.User;
import com.asgeek.books.persistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository implements UserAuthRepositoryDTO {

    @Autowired
    private UserCrudRepository userCrudRepository;

    @Autowired
    private UserMapper mapper;

    @Override
    public Optional<UserAuth> getUserByEmail(String email) {
        return userCrudRepository.findByEmail(email)
                .map(user -> mapper.toUserAuth(user));
    }

    @Override
    public Optional<UserAuth> getUserByUsername(String username) {
        return userCrudRepository.findByUsername(username)
                .map(user -> mapper.toUserAuth(user));
    }

    @Override
    public UserAuth save(UserAuth userAuth) {
        String passwordHash = BCrypt.hashpw(userAuth.getPassword(), BCrypt.gensalt());

        User user = mapper.toUser(userAuth);
        user.setPassword(passwordHash);

        return mapper.toUserAuth(userCrudRepository.save(user));
    }
}
