package com.example.cardbordcollector.dao;

import com.example.cardbordcollector.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    @Query("FROM User users JOIN FETCH users.listesRole WHERE pseudo = :pseudo")
    Optional<User> trouverParPseusoAvecRoles(@Param("pseudo") String pseudo);

    @Query( "FROM User users " +
            "JOIN FETCH users.listCollection c " +
            "WHERE pseudo = :pseudo " +
            "ORDER BY n.id DESC")
    Optional<User> trouverParPseudo(@Param("pseudo") String pseudo);
}
