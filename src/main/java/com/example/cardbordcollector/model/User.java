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

    private String pseudo;

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    private List<Collection> listCollection;

}
