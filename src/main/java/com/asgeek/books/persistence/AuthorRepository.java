package com.asgeek.books.persistence;

import com.asgeek.books.domain.dto.AuthorDTO;
import com.asgeek.books.domain.repository.AuthorRepositoryDTO;
import com.asgeek.books.persistence.crud.AuthorCrudRepository;
import com.asgeek.books.persistence.entity.Author;
import com.asgeek.books.persistence.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements AuthorRepositoryDTO {

    @Autowired
    private AuthorCrudRepository authorCrudRepository;

    @Autowired
    private AuthorMapper mapper;

    @Override
    public List<AuthorDTO> getAll() {
        List<Author> authors = (List<Author>) authorCrudRepository.findAll();
        return mapper.toAuthorsDTO(authors);
    }

    @Override
    public Optional<AuthorDTO> getAuthor(int authorId) {
        return authorCrudRepository.findById(authorId).map(author -> mapper.toAuthorDTO(author));
    }

    @Override
    public AuthorDTO save(AuthorDTO author) {
        Author newAuthor = mapper.toAuthor(author);
        return mapper.toAuthorDTO(authorCrudRepository.save(newAuthor));
    }

    @Override
    public void delete(int authorId) {
        authorCrudRepository.deleteById(authorId);
    }
}
