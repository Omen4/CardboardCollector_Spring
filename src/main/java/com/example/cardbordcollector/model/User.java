package com.example.cardbordcollector.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private int id;
    private String pseudo;
    private String motDePasse;

    @OneToMany
    private List<Collection> listCollection;
}
