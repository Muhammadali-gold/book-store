package com.strore.book.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "p01_address")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address extends BaseEntity {


    @Column
    private String city;

    @Column
    private String street;

    @Column(name = "home_number")
    @JsonProperty(value = "home_number")
    private String homeNumber;

//getters and setters


    @Override
    public String toString() {
        return "Address{" +
                "id=" + getId() +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", homeNumber='" + homeNumber + '\'' +
                '}';
    }
}
