package com.asgeek.books.domain.dto;

import lombok.Data;

@Data
public class OrderDetailDTO {
    // Atributos
    private int bookId;
    private int quantity;
    private boolean active;
    private double total;
}
