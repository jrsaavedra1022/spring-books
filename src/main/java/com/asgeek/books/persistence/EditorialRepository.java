package com.asgeek.books.persistence;

import com.asgeek.books.domain.dto.EditorialDTO;
import com.asgeek.books.domain.repository.EditorialRepositoryDTO;
import com.asgeek.books.persistence.crud.EditorialCrudRepository;
import com.asgeek.books.persistence.entity.Editorial;
import com.asgeek.books.persistence.mapper.EditorialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EditorialRepository implements EditorialRepositoryDTO {

    @Autowired
    private EditorialCrudRepository editorialCrudRepository;

    @Autowired
    private EditorialMapper mapper;

    @Override
    public List<EditorialDTO> getAll() {
        List<Editorial> editorials = (List<Editorial>) editorialCrudRepository.findAll();
        return mapper.toEditorialsDTO(editorials);
    }

    @Override
    public Optional<EditorialDTO> getEditorial(int editorialId) {
        return editorialCrudRepository.findById(editorialId).map(editorial -> mapper.toEditorialDTO(editorial));
    }

    @Override
    public EditorialDTO save(EditorialDTO editorial) {
        Editorial newEditorial = mapper.toEditorial(editorial);
        return mapper.toEditorialDTO(editorialCrudRepository.save(newEditorial));
    }

    @Override
    public void delete(int editorialId) {
        editorialCrudRepository.deleteById(editorialId);
    }
}
