package com.asgeek.books.domain.repository;

import com.asgeek.books.domain.dto.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface BookRepositoryDTO {

    List<BookDTO> getAll();

    Page<BookDTO> getAll(Pageable pageable);

    List<BookDTO> searchBook(BookDTO bookDTO);
    Optional<BookDTO> getBook(int bookId);

    List<BookDTO> getBooksByAuthor(int authorId);
    List<BookDTO> getBooksByCategory(int categoryId);
    BookDTO save(BookDTO book);
    void delete(int bookId);

}
