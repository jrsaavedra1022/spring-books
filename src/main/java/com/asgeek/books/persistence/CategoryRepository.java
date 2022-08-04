package com.asgeek.books.persistence;

import com.asgeek.books.domain.dto.CategoryDTO;
import com.asgeek.books.domain.repository.CategoryRepositoryDTO;
import com.asgeek.books.persistence.crud.CategoryCrudRepository;
import com.asgeek.books.persistence.entity.Category;
import com.asgeek.books.persistence.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository implements CategoryRepositoryDTO {

    @Autowired
    private CategoryCrudRepository categoryCrudRepository;

    @Autowired
    private CategoryMapper mapper;

    @Override
    public List<CategoryDTO> getAll() {
        List<Category> categories = (List<Category>) categoryCrudRepository.findAll();
        return mapper.toCategoriesDTO(categories);
    }

    @Override
    public Optional<CategoryDTO> getCategory(int categoryId) {
        return categoryCrudRepository.findById(categoryId).map(category -> mapper.toCategoryDTO(category));
    }

    @Override
    public CategoryDTO save(CategoryDTO category) {
        Category newCategory = mapper.toCategory(category);
        return mapper.toCategoryDTO(categoryCrudRepository.save(newCategory));
    }

    @Override
    public void delete(int categoryId) {
        categoryCrudRepository.deleteById(categoryId);
    }
}
