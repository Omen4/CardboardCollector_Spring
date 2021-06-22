package com.example.cardbordcollector.dao;

import com.example.cardbordcollector.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardDao extends JpaRepository<Card,Integer> {

}
