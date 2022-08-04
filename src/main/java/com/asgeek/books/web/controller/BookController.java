package com.asgeek.books.web.controller;

import com.asgeek.books.domain.dto.BookDTO;
import com.asgeek.books.domain.service.BookService;
import com.asgeek.books.domain.service.FilesStorageService;
import com.asgeek.books.utils.UtilException;
import com.asgeek.books.utils.UtilFieldValidation;
import com.asgeek.books.web.exceptions.GenericResponseException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.*;

@RestController
@Tag(name = "Books", description = "books resources")
@RequestMapping("/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private static final String IMAGE_FOLDER = "pictures/";
    @Autowired
    private BookService bookService;

    @Autowired
    private FilesStorageService filesStorageService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        List<BookDTO> books;
        Pageable paging = PageRequest.of(page, size);
        Page<BookDTO> bookDTOPage = bookService.getAll(paging);
        books = bookDTOPage.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("books", books);
        response.put("currentPage", bookDTOPage.getNumber());
        response.put("totalItems", bookDTOPage.getTotalElements());
        response.put("totalPages", bookDTOPage.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable("id") int bookId){
        return bookService.getBook(bookId)
                .map(bookDTO -> new ResponseEntity<>(bookDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/authors/{author_id}")
    public ResponseEntity<List<BookDTO>> getBooksByAuthor(@PathVariable("author_id") int authorId){
        return new ResponseEntity<>(bookService.getBooksByAuthor(authorId), HttpStatus.OK);
    }

    @GetMapping("/categories/{category_id}")
    public ResponseEntity<List<BookDTO>> getBooksByCategory(@PathVariable("category_id") int categoryId){
        return new ResponseEntity<>(bookService.getBooksByCategory(categoryId), HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<BookDTO>> search(@RequestBody BookDTO bookDTO){
        return new ResponseEntity<>(bookService.searchBook(bookDTO), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookDTO> save(@RequestBody BookDTO bookDTO){
        try{
            List<Object> errors = UtilFieldValidation.getInstance().inputFieldValidation(bookDTO, "BookDTO");
            if(!errors.isEmpty()){
                Map<String, List<Object>> response = new HashMap<>();
                response.put("errors", errors);
                throw new GenericResponseException(response, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(bookService.save(bookDTO), HttpStatus.CREATED);
        }catch(UtilException ex){
            logger.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}/picture")
    public ResponseEntity<BookDTO> uploadPicture(@PathVariable("id") int bookId, @RequestParam("picture")MultipartFile picture){
        try{
            BookDTO bookDTO = bookService.getBook(bookId).orElse(null);
            if (bookDTO == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            String filename = bookDTO.getCode().toString()  + "." + Objects.requireNonNull(picture.getOriginalFilename()).split("\\.")[1];
            filesStorageService.save(picture, IMAGE_FOLDER + filename);
            bookDTO.setPicture( IMAGE_FOLDER +  filename);

            // Guardamos el libro con la imagen actualizada
            BookDTO newBookDTO = bookService.save(bookDTO);
            String url = MvcUriComponentsBuilder.fromMethodName(BookController.class, "getBookPicture", filename).build().toString();
            newBookDTO.setPicture(url);

            return new ResponseEntity<>(newBookDTO, HttpStatus.OK);
        }catch (UtilException ex){
            logger.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/"+ IMAGE_FOLDER +"{filename}")
    public ResponseEntity<Resource> getBookPicture(@PathVariable("filename") String filename){
        try{
            String defaultFilename = IMAGE_FOLDER + filename;
            Resource file =  filesStorageService.load(defaultFilename);

            // Headers
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE);

            // # Descargar el archivo:
            /**
             * responseHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file_w.getFilename() + "\"");
             */


            return new ResponseEntity<>(file, responseHeaders, HttpStatus.OK);
        }catch(UtilException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int bookId){
        if(bookService.delete(bookId))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
