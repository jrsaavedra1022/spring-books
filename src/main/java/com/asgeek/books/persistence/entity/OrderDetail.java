package com.asgeek.books.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_details")
public class OrderDetail {

    // Atributos
    @EmbeddedId
    private OrderDetailPK id;

    private Integer quantity;
    private Boolean state;
    private Double total;

    // llaves foraneas
    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private Book book;


}
