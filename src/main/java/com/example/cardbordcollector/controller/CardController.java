package com.example.cardbordcollector.controller;

import com.example.cardbordcollector.dao.CardDao;
import com.example.cardbordcollector.model.Card;
import com.example.cardbordcollector.model.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@CrossOrigin
public class CardController {

    CardDao cardDao;

    @Autowired
    CardController(CardDao cardDao){this.cardDao = cardDao; }

    @PostMapping("/user/card")
    public ResponseEntity<Collection> addCard (@RequestBody Card card){
        card = cardDao.saveAndFlush(card);
        return ResponseEntity.created(
                URI.create("/user/collection/"+card.getId())
        ).build();
    }

    @PostMapping("/user/card/{id}")
    public ResponseEntity<Integer> deleteCard (@PathVariable int id){
        if(cardDao.existsById(id)){
            cardDao.deleteById(id);
            return ResponseEntity.ok(id);
        }else{
            return ResponseEntity.noContent().build();
        }

    }


}
