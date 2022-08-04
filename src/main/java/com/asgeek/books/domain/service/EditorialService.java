package com.asgeek.books.domain.service;

import com.asgeek.books.domain.dto.EditorialDTO;
import com.asgeek.books.domain.repository.EditorialRepositoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditorialService {

    @Autowired
    private EditorialRepositoryDTO editorialRepositoryDTO;

    public List<EditorialDTO> getAll(){
        return editorialRepositoryDTO.getAll();
    }

    public Optional<EditorialDTO> getEditorial(int editorialId){
        return editorialRepositoryDTO.getEditorial(editorialId);
    }

    public EditorialDTO save(EditorialDTO editorial){
        return editorialRepositoryDTO.save(editorial);
    }

    public boolean delete(int editorialId){
        return getEditorial(editorialId).map(editorialDTO -> {
            editorialRepositoryDTO.delete(editorialId);
            return true;
        }).orElse(false);
    }

}
