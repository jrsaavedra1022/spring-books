package com.asgeek.books.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "books")
public class Book {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private UUID code;
    private String title;

    @Column(name = "author_id")
    private Integer authorId;

    @Column(name = "editorial_id")
    private Integer editorialId;

    private String description;

    @Column(name = "edition_number")
    private Integer editionNumber;

    @Column(name = "category_id")
    private Integer categoryId;

    private Double price;
    private Integer stock;
    private Boolean state;
    private String picture;

    // llaves foraneas
    @ManyToOne
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name = "editorial_id", insertable = false, updatable = false)
    private Editorial editorial;

    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;
}
