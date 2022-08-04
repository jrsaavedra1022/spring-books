package com.asgeek.books.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "clients")
public class Client {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "document_type")
    private String documentType;

    @Column(unique = true)
    private String document;

    @Column(name = "last_name")
    private String lastName;
    private String cellphone;
    private String address;
    private String email;

    // reverse llaves foraneas
    @OneToMany(mappedBy = "client", cascade = { CascadeType.ALL })
    private List<Payment> payments;

    @OneToMany(mappedBy = "client", cascade = { CascadeType.ALL })
    private List<Order> orders;

}
