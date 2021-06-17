package com.example.cardbordcollector.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Collection {

    @ManyToOne
    private User user;
    private String tcg;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    private List<Card> cardList;

    public Collection(User user, String tcg, List<Card> cardList){
        this.user = user;
        this.tcg = tcg;
        this.cardList = cardList;

    }

    public Collection() {

    }
}
