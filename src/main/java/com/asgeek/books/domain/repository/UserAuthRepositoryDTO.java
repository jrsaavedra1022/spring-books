package com.asgeek.books.domain.repository;

import com.asgeek.books.domain.auth.UserAuth;

import java.util.Optional;

public interface UserAuthRepositoryDTO {

    Optional<UserAuth> getUserByEmail(String email);

    Optional<UserAuth> getUserByUsername(String username);

    UserAuth save(UserAuth userAuth);

}
