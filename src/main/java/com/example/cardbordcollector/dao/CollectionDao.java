package com.example.cardbordcollector.dao;

import com.example.cardbordcollector.model.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionDao extends JpaRepository<Collection, Integer> {
}
