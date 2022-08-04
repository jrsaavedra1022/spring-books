package com.asgeek.books.domain.dto;

import lombok.Data;

@Data
public class ClientDTO {
    // Atributos
    private int id;
    private String documentType;
    private String document;
    private String firstName;
    private String lastName;
    private String cellphone;
    private String address;
    private String email;
}
