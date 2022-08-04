package com.asgeek.books.domain.service;

import com.asgeek.books.domain.dto.AuthorDTO;
import com.asgeek.books.domain.repository.AuthorRepositoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepositoryDTO authorRepositoryDTO;

    public List<AuthorDTO> getAll(){
        return authorRepositoryDTO.getAll();
    }

    public Optional<AuthorDTO> getAuthor(int authorId){
       return authorRepositoryDTO.getAuthor(authorId);
    }

    public AuthorDTO save(AuthorDTO author){
        return authorRepositoryDTO.save(author);
    }

    public boolean delete(int authorId){
        return getAuthor(authorId).map(authorDTO -> {
            authorRepositoryDTO.delete(authorId);
            return true;
        }).orElse(false);
    }
}
