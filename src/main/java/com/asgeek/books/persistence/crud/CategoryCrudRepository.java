package com.asgeek.books.persistence.crud;

import com.asgeek.books.persistence.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryCrudRepository extends CrudRepository<Category, Integer> {
}
