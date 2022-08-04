package com.asgeek.books.web.controller;

import com.asgeek.books.domain.dto.CategoryDTO;
import com.asgeek.books.domain.service.CategoryService;
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
@Tag(name = "Categories", description = "categories resources")
@RequestMapping("/categories")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll(){
        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable("id") int categoryId){
        return categoryService.getCategory(categoryId)
                .map(categoryDTO -> new ResponseEntity<>(categoryDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> save(@RequestBody CategoryDTO categoryDTO){
        try{
            List<Object> errors = UtilFieldValidation.getInstance().inputFieldValidation(categoryDTO, "CategoryDTO");
            if(!errors.isEmpty()){
                Map<String, List<Object>> response = new HashMap<>();
                response.put("errors", errors);
                throw new GenericResponseException(response, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(categoryService.save(categoryDTO), HttpStatus.CREATED);
        }catch(UtilException ex){
            logger.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int authorId){
        if(categoryService.delete(authorId))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
