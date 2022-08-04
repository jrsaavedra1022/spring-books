package com.asgeek.books.persistence.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Data
@Embeddable
public class PaymentPK implements Serializable {
    // Atributos
    @Column(name = "client_id")
    private Integer clientId;

    @Column(name = "invoice_number", nullable = false, unique = true)
    private UUID invoiceNumber;
}
