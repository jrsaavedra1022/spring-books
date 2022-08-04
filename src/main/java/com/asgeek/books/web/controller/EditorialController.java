package com.asgeek.books.web.controller;

import com.asgeek.books.domain.dto.EditorialDTO;
import com.asgeek.books.domain.service.EditorialService;
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
@Tag(name = "Editorials", description = "editorials resources")
@RequestMapping("/editorials")
public class EditorialController {

    private static final Logger logger = LoggerFactory.getLogger(EditorialController.class);
    @Autowired
    private EditorialService editorialService;

    @GetMapping
    public ResponseEntity<List<EditorialDTO>> getAll(){
        return new ResponseEntity<>(editorialService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EditorialDTO> getEditorial(@PathVariable("id") int editorialId){
        return editorialService.getEditorial(editorialId)
                .map(editorialDTO -> new ResponseEntity<>(editorialDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<EditorialDTO> save(@RequestBody EditorialDTO editorialDTO){
        try{
            List<Object> errors = UtilFieldValidation.getInstance().inputFieldValidation(editorialDTO, "EditorialDTO");
            if(!errors.isEmpty()){
                Map<String, List<Object>> response = new HashMap<>();
                response.put("errors", errors);
                throw new GenericResponseException(response, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(editorialService.save(editorialDTO), HttpStatus.CREATED);
        }catch(UtilException ex){
            logger.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int editorialId){
        if(editorialService.delete(editorialId)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
