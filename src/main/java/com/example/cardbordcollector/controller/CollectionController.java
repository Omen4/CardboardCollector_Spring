package com.example.cardbordcollector.controller;

import com.example.cardbordcollector.dao.CardDao;
import com.example.cardbordcollector.dao.CollectionDao;
import com.example.cardbordcollector.model.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@CrossOrigin
public class CollectionController {

    CollectionDao collectionDao;

    @Autowired
    CollectionController(CollectionDao collectionDao){this.collectionDao = collectionDao; }

    @PostMapping("/user/collection")
    public ResponseEntity<Collection> addCollection (@RequestBody Collection collection){
        collection = collectionDao.saveAndFlush(collection);
        return ResponseEntity.created(
                URI.create("/user/collection/"+collection.getId())
        ).build();
    }

    @PostMapping("/user/collection/{id}")
    public ResponseEntity<Integer> deleteCollection (@PathVariable int id){
        if(collectionDao.existsById(id)){
            collectionDao.deleteById(id);
            return ResponseEntity.ok(id);
        }else{
            return ResponseEntity.noContent().build();
        }

    }
}
