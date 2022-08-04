package com.asgeek.books.domain.repository;

import com.asgeek.books.domain.dto.CategoryDTO;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryDTO {

    List<CategoryDTO> getAll();
    Optional<CategoryDTO> getCategory(int categoryId);
    CategoryDTO save(CategoryDTO category);
    void delete(int categoryId);

}
