package com.asgeek.books.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "received_date")
    private LocalDateTime receivedDate;

    @Column(name = "delivery_deadline")
    private LocalDateTime deliveryDeadline;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    private String state;
    private String observation;

    @Column(name = "client_id")
    private Integer clientId;

    // llaves foraneas
    @ManyToOne
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private Client client;

    // reverse llaves foraneas
    @OneToMany(mappedBy = "order", cascade = { CascadeType.ALL })
    private List<OrderDetail> orderDetails;
}
