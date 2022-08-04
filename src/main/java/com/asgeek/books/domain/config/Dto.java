package com.asgeek.books.domain.config;

import lombok.Data;

@Data
public class Dto {
    // Atributos
    private Integer id;
    private String dtoName;
    private String dtoClasName;
    private String dtoField;
    private Boolean dtoIsRequired;
    private Boolean dtoIsObject;
    private String dtoDatatype;
    private String entityField;
    private Boolean isMapper;
}
