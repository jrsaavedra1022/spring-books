package com.asgeek.books.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "authors")
public class Author {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "country_id")
    private Integer countryId;

    // llaves foraneas
    @ManyToOne
    @JoinColumn(name = "country_id", insertable = false, updatable = false)
    private Country country;

    // reverse llaves foreneas
    @OneToMany(mappedBy = "author", cascade = { CascadeType.ALL })
    private List<Book> books;
}
