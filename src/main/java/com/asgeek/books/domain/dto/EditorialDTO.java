package com.asgeek.books.domain.dto;

import lombok.Data;

@Data
public class EditorialDTO {
    // Atributos
    private int id;
    private String editorial;
    private int countryId;
    private CountryDTO country;
}
