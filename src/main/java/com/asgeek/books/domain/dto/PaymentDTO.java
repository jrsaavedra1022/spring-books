package com.asgeek.books.domain.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PaymentDTO {
    // Atributos
    private int clientId;
    private UUID invoiceNumber;
    private String paymentMethod;
    private double total;
}
