package com.asgeek.books.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "payments")
public class Payment {

    // Atributos
    @EmbeddedId
    private PaymentPK id;

    @Column(name = "payment_method")
    private String paymentMethod;

    private Double total;

    // llaves foraneas
    @ManyToOne
    @MapsId("clientId")
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private Client client;

}
