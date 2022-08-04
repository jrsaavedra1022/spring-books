package com.asgeek.books.domain.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class BookDTO {
    // Atributos
    private int id;
    private UUID code;
    private String title;
    private int authorId;
    private int editorialId;
    private String description;
    private int editionNumber;
    private int categoryId;
    private double price;
    private int stock;
    private boolean available;
    private String picture;
    private AuthorDTO author;
    private EditorialDTO editorial;
    private CategoryDTO category;
}
