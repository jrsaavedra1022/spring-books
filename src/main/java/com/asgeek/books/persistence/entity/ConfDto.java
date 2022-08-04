package com.asgeek.books.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "conf_dtos")
public class ConfDto {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dto_name")
    private String dtoName;

    @Column(name = "dto_class_name")
    private String dtoClasName;

    @Column(name = "dto_field")
    private String dtoField;

    @Column(name = "dto_is_required")
    private Boolean dtoIsRequired;

    @Column(name = "dto_is_object")
    private Boolean dtoIsObject;

    @Column(name = "dto_datatype")
    private String dtoDatatype;

    @Column(name = "entity_field")
    private String entityField;

    @Column(name = "is_mapper")
    private Boolean isMapper;
}
