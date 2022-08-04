package com.asgeek.books.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "conf_response_codes")
public class ConfResponseCode {
    // Atributos
    @Id
    private String code;

    private String message;
    private String description;
}
