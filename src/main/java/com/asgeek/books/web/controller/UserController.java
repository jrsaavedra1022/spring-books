package com.asgeek.books.web.controller;

import com.asgeek.books.domain.auth.UserAuth;
import com.asgeek.books.domain.service.UserAuthService;
import com.asgeek.books.utils.UtilException;
import com.asgeek.books.utils.UtilFieldValidation;
import com.asgeek.books.web.error.GenericError;
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
@Tag(name = "users", description = "users resources")
@RequestMapping(value = "/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserAuthService userAuthService;

    @GetMapping("/{username}")
    public ResponseEntity<UserAuth> getUserByUsername(@PathVariable("username") String username){
        return userAuthService.getUserByUsername(username)
                .map(userAuth -> new ResponseEntity<>(userAuth, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/search")
    public ResponseEntity<UserAuth> getUserByEmail(@RequestBody UserAuth user){
        if(user.getEmail() == null)
            throw new GenericResponseException(GenericError.EMAIL_IS_REQUIRED, HttpStatus.BAD_REQUEST);

        return userAuthService.getUserByEmail(user.getEmail())
                .map(userAuth -> new ResponseEntity<>(userAuth, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<UserAuth> save(@RequestBody UserAuth userAuth){
        try{
            List<Object> errors = UtilFieldValidation.getInstance().inputFieldValidation(userAuth, "UserAuth");
            if(!errors.isEmpty()){
                Map<String, List<Object>> response = new HashMap<>();
                response.put("errors", errors);
                throw new GenericResponseException(response, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(userAuthService.save(userAuth), HttpStatus.CREATED);
        }catch(UtilException ex){
            logger.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
