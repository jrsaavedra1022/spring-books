package com.asgeek.books.domain.auth;

import lombok.Data;

import java.util.Date;

@Data
public class UserAuth {
    //Atributos
    private Integer id;
    private String username;
    private String email;
    private String password;
    private boolean active;
    private Date createdAt;
}
