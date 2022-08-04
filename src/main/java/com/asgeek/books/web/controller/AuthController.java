package com.asgeek.books.web.controller;

import com.asgeek.books.domain.auth.AuthenticationRequest;
import com.asgeek.books.domain.auth.AuthenticationResponse;
import com.asgeek.books.domain.auth.UserAuth;
import com.asgeek.books.web.error.GenericError;
import com.asgeek.books.domain.service.UserAuthService;
import com.asgeek.books.web.exceptions.GenericResponseException;
import com.asgeek.books.web.security.JWTGenerate;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Auth", description = "resources for authentication")
@RequestMapping("/auth")
public class AuthController {

    private final JWTGenerate jwtGenerate;
    private final UserAuthService userAuthService;

    public AuthController(@Autowired UserAuthService userAuthService, @Autowired JWTGenerate jwtGenerate){
        this.userAuthService = userAuthService;
        this.jwtGenerate = jwtGenerate;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> auth(@RequestBody AuthenticationRequest request){
        UserAuth userAuth = userAuthService.getUserByEmail(request.getEmail()).orElse(null);
        if (userAuth == null)
            throw new GenericResponseException(GenericError.INVALID_USER_CREDENTIALS, HttpStatus.FORBIDDEN);

        String jwt;
        if(BCrypt.checkpw(request.getPassword(), userAuth.getPassword())){
            jwt = jwtGenerate.generateTokenV2(userAuth);
            return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
        } else {
            throw new GenericResponseException(GenericError.INVALID_USER_CREDENTIALS, HttpStatus.FORBIDDEN);
        }
    }
}
