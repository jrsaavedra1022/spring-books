package com.asgeek.books.domain.config;

import lombok.Data;

@Data
public class ResponseCode {
    // Atributos
    private String code;
    private String message;
    private String description;
}
