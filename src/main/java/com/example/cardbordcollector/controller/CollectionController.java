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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class CollectionController {

    private CollectionDao collectionDao;
    private UtilisateurDao userDao;
    private CardDao cardDao;
    private JwtUtil jwtUtil;

    @Autowired
    CollectionController(CollectionDao collectionDao,
                         UtilisateurDao userDao,
                         CardDao cardDao,
                         JwtUtil jwtUtil){
        this.collectionDao = collectionDao;
        this.userDao = userDao;
        this.cardDao = cardDao;
        this.jwtUtil = jwtUtil;
    }

    //Ajouter une collection à un utilisateur
    @PostMapping("/user/collection")
    public ResponseEntity<Collection> addCollection (@RequestBody Collection collection){
        collection = collectionDao.saveAndFlush(collection);
        return ResponseEntity.created(
                URI.create("/user/collection/"+collection.getId())
        ).build();
    }

    //Supprimer une collection à un utilisateur
    @PostMapping("/user/collection/{id}")
    public ResponseEntity<Integer> deleteCollection (@PathVariable int id){
        if(collectionDao.existsById(id)){
            collectionDao.deleteById(id);
            return ResponseEntity.ok(id);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

//    //Récupérer les cartes d'une collection d'un utilisateur selon le CCG
//    @GetMapping("/user/collection/{id}")
//    public ResponseEntity<List<Card>> getUserCardsInCollection (@PathVariable int id){
//        if (collectionDao.existsById(id)){
//            return ResponseEntity.ok(collectionDao.getById(id).getListCard());
//        }else{
//            return ResponseEntity.noContent().header("NO_CONTENT", "0").build();
//        }
//    }

    //Check les collections d'un utilisateur
    @GetMapping("/collections")
    public ResponseEntity<List<Collection>> getCollections() {
        return ResponseEntity.ok(collectionDao.findAll());
    }

    //Afficher les cartes de la collection de l'utilisateur
    @GetMapping("/user/{id}/collection/")
    public ResponseEntity<List<Collection>> getCollectionFromUser(@PathVariable int id) {
        if (userDao.existsById(id)){
            return ResponseEntity.ok(userDao.getById(id).getListCollection());
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/user/mycollection/mycards")
    public ResponseEntity<List<Card>> getMyCollection(
            @RequestHeader(value = "Authorization") String authorization) {

        String token = authorization.substring(7);
        String username = jwtUtil.getTokenBody(token).getSubject();
        Optional<Utilisateur> utilisateur = userDao.trouverParPseudo(username);

        if (utilisateur.isPresent()) {
            int userId = utilisateur.get().getId();
            //Passer l'idée de la collection dans le futur (plusieurs CCG)
            return ResponseEntity.ok().body( utilisateur.get().getListCollection().get(0).getListCard());
        }

        return ResponseEntity.notFound().build();
    }


}
