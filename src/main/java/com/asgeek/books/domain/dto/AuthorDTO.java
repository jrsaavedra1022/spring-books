package com.asgeek.books.domain.dto;

import lombok.Data;

@Data
public class AuthorDTO {
    // Atributos
    private int id;
    private String name;
    private int countryId;
    private CountryDTO country;
}
