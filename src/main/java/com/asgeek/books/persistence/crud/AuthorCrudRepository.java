package com.asgeek.books.persistence.crud;

import com.asgeek.books.persistence.entity.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorCrudRepository extends CrudRepository<Author, Integer> {
}
