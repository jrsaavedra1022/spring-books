package com.asgeek.books.domain.auth;

import lombok.Data;

@Data
public class AuthenticationRequest {
    // Atributos
    private String email;
    private String password;
}
