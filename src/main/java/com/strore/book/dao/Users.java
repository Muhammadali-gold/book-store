package com.strore.book.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.transaction.Transactional;

@Data
@Entity
@Table(name = "p01_users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Users extends BaseEntity {

    @Column
    private String name;

    @Column
    private String email;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @Override
    public String toString() {
        return "Users{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address.toString() +
                '}';
    }
}
