package com.asgeek.books.persistence;

import com.asgeek.books.domain.dto.BookDTO;
import com.asgeek.books.domain.repository.BookRepositoryDTO;
import com.asgeek.books.persistence.crud.BookCrudRepository;
import com.asgeek.books.persistence.entity.Book;
import com.asgeek.books.persistence.mapper.BookMapper;
import com.asgeek.books.utils.UtilException;
import com.asgeek.books.utils.UtilMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class BookRepository implements BookRepositoryDTO {

    private static final Logger logger = LoggerFactory.getLogger(BookRepository.class);
    @Autowired
    private BookCrudRepository bookCrudRepository;

    @Autowired
    private BookMapper mapper;

    @Override
    public List<BookDTO> getAll() {
        List<Book> books = (List<Book>) bookCrudRepository.findAll();
        return mapper.toBooksDTO(books);
    }

    @Override
    public Page<BookDTO> getAll(Pageable pageable){
        Page<Book> books = bookCrudRepository.findAll(pageable);
        return books.map(book -> {
            try{
                Object dto = UtilMapper.getInstance().mapperDTOfromEntity(book,"BookDTO");
                return (BookDTO) dto;
            }catch(UtilException ex){
                logger.error(ex.getMessage());
                return new BookDTO();
            }
        });
    }

    @Override
    public List<BookDTO> searchBook(BookDTO bookDTO) {
        return mapper.toBooksDTO(bookCrudRepository.findByTitle(bookDTO.getTitle()));
    }

    @Override
    public Optional<BookDTO> getBook(int bookId) {
        return bookCrudRepository.findById(bookId).map(book -> mapper.toBookDTO(book));
    }

    @Override
    public List<BookDTO> getBooksByAuthor(int authorId) {
        return mapper.toBooksDTO(bookCrudRepository.findByAuthorId(authorId));
    }

    @Override
    public List<BookDTO> getBooksByCategory(int categoryId) {
        return mapper.toBooksDTO(bookCrudRepository.findByCategoryId(categoryId));
    }

    @Override
    public BookDTO save(BookDTO book) {
        Book newBook = mapper.toBook(book);
        if(newBook.getCode() == null)
            newBook.setCode(UUID.randomUUID());

        return mapper.toBookDTO(bookCrudRepository.save(newBook));
    }

    @Override
    public void delete(int bookId) {
        bookCrudRepository.deleteById(bookId);
    }
}
