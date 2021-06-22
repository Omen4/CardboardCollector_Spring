package com.example.cardbordcollector.controller;

import com.example.cardbordcollector.dao.CardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CardController {

    CardDao cardDao;

    @Autowired
    CardController(CardDao cardDao){this.cardDao = cardDao; }


}
