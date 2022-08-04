package com.asgeek.books.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "countries")
public class Country {
    // atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String country;

    // reverse llaves foraneas
    @OneToMany(mappedBy = "country")
    private List<Author> authors;

    @OneToMany(mappedBy = "country")
    private List<Editorial> editorials;
}
