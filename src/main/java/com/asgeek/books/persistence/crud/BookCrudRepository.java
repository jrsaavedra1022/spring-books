package com.asgeek.books.persistence.crud;

import com.asgeek.books.persistence.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookCrudRepository extends CrudRepository<Book, Integer> {
    Page<Book> findAll(Pageable pageable);
    List<Book> findByAuthorId(Integer authorId);
    List<Book> findByCategoryId(Integer categoryId);

    @Query(value = "SELECT b FROM Book b where b.title like %:title%")
    List<Book> findByTitle(String title);
}
