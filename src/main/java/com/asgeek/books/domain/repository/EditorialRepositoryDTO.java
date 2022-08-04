package com.asgeek.books.domain.repository;

import com.asgeek.books.domain.dto.EditorialDTO;

import java.util.List;
import java.util.Optional;

public interface EditorialRepositoryDTO {

    List<EditorialDTO> getAll();
    Optional<EditorialDTO> getEditorial(int editorialId);
    EditorialDTO save(EditorialDTO editorial);
    void delete(int editorialId);

}
