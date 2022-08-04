package com.asgeek.books.web.controller;

import com.asgeek.books.domain.dto.AuthorDTO;
import com.asgeek.books.domain.service.AuthorService;
import com.asgeek.books.utils.UtilException;
import com.asgeek.books.utils.UtilFieldValidation;
import com.asgeek.books.web.exceptions.GenericResponseException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "Authors", description = "authors resources")
@RequestMapping(value = "/authors")
public class AuthorController {

    private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAll(){
        return new ResponseEntity<>(authorService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable("id") int authorId){
        return authorService.getAuthor(authorId)
                .map(authorDTO -> new ResponseEntity<>(authorDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> save(@RequestBody AuthorDTO authorDTO){
        try{
            List<Object> errors = UtilFieldValidation.getInstance().inputFieldValidation(authorDTO, "AuthorDTO");
            if(!errors.isEmpty()){
                Map<String, List<Object>> response = new HashMap<>();
                response.put("errors", errors);
                throw new GenericResponseException(response, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(authorService.save(authorDTO), HttpStatus.CREATED);
        }catch(UtilException ex){
            logger.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int authorId){
        if(authorService.delete(authorId))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
