package com.strore.book.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "p01_book")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book extends BaseEntity {

    @Column
    private String name;

    @Column
    private String author;

    @Column
    private int quantity;

    @Column
    private double price;

    @Column
    private double rating;

    @Column
    private String img;


}
