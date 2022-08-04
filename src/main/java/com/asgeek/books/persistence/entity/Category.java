package com.asgeek.books.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "categories")
public class Category {
    // atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    // reverse llaves foreneas
    @OneToMany(mappedBy = "category", cascade = { CascadeType.ALL })
    private List<Book> books;
}
