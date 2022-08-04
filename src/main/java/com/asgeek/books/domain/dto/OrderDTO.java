package com.asgeek.books.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    // Atributos
    private int id;
    private LocalDateTime receivedDate;
    private LocalDateTime deliveryDeadline;
    private LocalDateTime deliveryDate;
    private String observation;
    private String state;
    private int clientId;
    private List<OrderDetailDTO> details;
}
