package com.ironhack.authors.model.authors;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Version
    private int version;

    private String firstName;

    private String lastName;
}
