package com.example.cardbordcollector.controller;

import com.example.cardbordcollector.dao.CardDao;
import com.example.cardbordcollector.model.Card;
import com.example.cardbordcollector.model.Collection;
import com.example.cardbordcollector.model.Utilisateur;
import com.example.cardbordcollector.security.JwtUtil;
import com.example.cardbordcollector.view.VueUtilisateur;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
public class CardController {

    private CardDao cardDao;

    @Autowired
    CardController(CardDao cardDao){
        this.cardDao = cardDao;
    }

    @PostMapping("/card")
    public ResponseEntity<Collection> addCard (@RequestBody Card card){
        card = cardDao.saveAndFlush(card);
        return ResponseEntity.created(
                URI.create("/user/collection/"+card.getId())
        ).build();
    }

    @PostMapping("/card/{id}")
    public ResponseEntity<Integer> deleteCard (@PathVariable int id){
        if(cardDao.existsById(id)){
            cardDao.deleteById(id);
            return ResponseEntity.ok(id);
        }else{
            return ResponseEntity.noContent().build();
        }

    }

    @GetMapping("/cards")
    public ResponseEntity<List<Card>> getCards() {
        return ResponseEntity.ok(cardDao.findAll());
    }


}
