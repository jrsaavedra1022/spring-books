package com.asgeek.books.domain.service;

import com.asgeek.books.domain.dto.CategoryDTO;
import com.asgeek.books.domain.repository.CategoryRepositoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepositoryDTO categoryRepositoryDTO;

    public List<CategoryDTO> getAll(){
        return categoryRepositoryDTO.getAll();
    }

    public Optional<CategoryDTO> getCategory(int categoryId){
        return categoryRepositoryDTO.getCategory(categoryId);
    }

    public CategoryDTO save(CategoryDTO category){
        return categoryRepositoryDTO.save(category);
    }

    public boolean delete(int categoryId){
        return getCategory(categoryId).map(categoryDTO -> {
            categoryRepositoryDTO.delete(categoryId);
            return true;
        }).orElse(false);
    }
}
