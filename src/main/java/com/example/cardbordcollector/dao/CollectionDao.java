package com.example.cardbordcollector.dao;

import com.example.cardbordcollector.model.Collection;
import com.example.cardbordcollector.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollectionDao extends JpaRepository<Collection, Integer> {
}
