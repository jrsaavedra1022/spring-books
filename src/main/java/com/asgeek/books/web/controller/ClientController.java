package com.asgeek.books.web.controller;

import com.asgeek.books.domain.dto.ClientDTO;
import com.asgeek.books.domain.service.ClientService;
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
@Tag(name = "Clients", description = "clients resources")
@RequestMapping("/customers")
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAll(){
        return new ResponseEntity<>(clientService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getAuthor(@PathVariable("id") int clientId){
        return clientService.getClient(clientId)
                .map(clientDTO -> new ResponseEntity<>(clientDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/search")
    public ResponseEntity<List<ClientDTO>> search(@RequestBody ClientDTO clientDTO){
        return new ResponseEntity<>(clientService.searchClient(clientDTO), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> save(@RequestBody ClientDTO clientDTO){
        try{
            List<Object> errors = UtilFieldValidation.getInstance().inputFieldValidation(clientDTO, "ClientDTO");
            if(!errors.isEmpty()){
                Map<String, List<Object>> response = new HashMap<>();
                response.put("errors", errors);
                throw new GenericResponseException(response, HttpStatus.BAD_REQUEST);
            }
        return new ResponseEntity<>(clientService.save(clientDTO), HttpStatus.CREATED);
        }catch(UtilException ex){
            logger.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int clientId){
        if(clientService.delete(clientId))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
