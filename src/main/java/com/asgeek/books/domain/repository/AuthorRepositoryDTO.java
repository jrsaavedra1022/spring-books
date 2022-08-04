package com.asgeek.books.domain.repository;

import com.asgeek.books.domain.dto.AuthorDTO;

import java.util.List;
import java.util.Optional;

public interface AuthorRepositoryDTO {

    List<AuthorDTO> getAll();
    Optional<AuthorDTO> getAuthor(int authorId);
    AuthorDTO save(AuthorDTO author);
    void delete(int authorId);
}
