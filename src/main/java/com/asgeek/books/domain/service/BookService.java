package com.asgeek.books.domain.service;

import com.asgeek.books.domain.dto.BookDTO;
import com.asgeek.books.domain.repository.BookRepositoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepositoryDTO bookRepositoryDTO;

    public List<BookDTO> getAll(){
        return bookRepositoryDTO.getAll();
    }

    public Page<BookDTO> getAll(Pageable pageable){
        return bookRepositoryDTO.getAll(pageable);
    }

    public List<BookDTO> searchBook(BookDTO bookDTO){ return bookRepositoryDTO.searchBook(bookDTO); }
    public Optional<BookDTO> getBook(int bookId) {
        return bookRepositoryDTO.getBook(bookId);
    }
    public List<BookDTO> getBooksByAuthor(int authorId)  {
        return bookRepositoryDTO.getBooksByAuthor(authorId);
    }

    public List<BookDTO> getBooksByCategory(int categoryId) {
        return bookRepositoryDTO.getBooksByCategory(categoryId);
    }

    public BookDTO save(BookDTO book){
        return bookRepositoryDTO.save(book);
    }

    public boolean delete(int bookId){
        return getBook(bookId).map(bookDTO -> {
            bookRepositoryDTO.delete(bookId);
            return true;
        }).orElse(false);
    }

}
