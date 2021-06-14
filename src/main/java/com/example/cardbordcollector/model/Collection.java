package com.example.cardbordcollector.model;

import java.util.List;

public class Collection {

    private User user;
    private String tcg;
    private List<Card> cardList;

    public Collection(User user, String tcg, List<Card> cardList){
        this.user = user;
        this.tcg = tcg;
        this.cardList = cardList;

    }
}
