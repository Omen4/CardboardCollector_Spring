package com.example.cardbordcollector.controller;

import com.example.cardbordcollector.dao.CardDao;
import com.example.cardbordcollector.dao.CollectionDao;
import com.example.cardbordcollector.dao.UtilisateurDao;
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
import java.util.Optional;

@RestController
@CrossOrigin
public class CardController {

    private CardDao cardDao;
    private JwtUtil jwtUtil;
    private CollectionDao collectionDao;
    private UtilisateurDao userDao;


    @Autowired
    CardController(CardDao cardDao,
                   JwtUtil jwtUtil,
                   CollectionDao collectionDao,
                   UtilisateurDao userDao){
        this.cardDao = cardDao;
        this.jwtUtil = jwtUtil;
        this.collectionDao = collectionDao;
        this.userDao = userDao;
    }

    @PostMapping("/card/{id}")
    public ResponseEntity addCard (@PathVariable int id,
                                   @RequestHeader(value = "Authorization") String authorization) {

        String token = authorization.substring(7);
        String username = jwtUtil.getTokenBody(token).getSubject();
        Optional<Utilisateur> utilisateur = userDao.trouverParPseudo(username);
        if (utilisateur.isPresent()) {
           Optional<Card> card = cardDao.findById(id);
           if(card.isPresent()){
               List<Card> listCard = utilisateur.get().getListCollection().get(0).getListCard();
               if(listCard.contains(card.get())){
                  return ResponseEntity.ok().build();
               }else{
                   listCard.add(card.get());
                   collectionDao.saveAndFlush(utilisateur.get().getListCollection().get(0));
                   userDao.saveAndFlush(utilisateur.get());
                   return ResponseEntity.ok().build();
               }
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/card/{id}")
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
