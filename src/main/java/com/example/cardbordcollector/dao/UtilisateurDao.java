package com.example.cardbordcollector.dao;

import com.example.cardbordcollector.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
//
//@Repository
//public interface UtilisateurDao extends JpaRepository<Utilisateur, Integer> {
//    @Query("FROM Utilisateur u JOIN FETCH u.listeRole WHERE pseudo = :pseudo")
//    Optional<Utilisateur> trouverParPseusoAvecRoles(@Param("pseudo") String pseudo);
//
//    @Query( "FROM Utilisateur u " +
//            "JOIN FETCH u.listeNote n " +
//            "WHERE pseudo = :pseudo " +
//            "ORDER BY n.id DESC")
//    Optional<Utilisateur> trouverParPseudo(@Param("pseudo") String pseudo);


@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur, Integer> {

    @Query("FROM Utilisateur u JOIN FETCH u.listeRole WHERE u.pseudo = :pseudo")
    Optional<Utilisateur> trouverParPseudoAvecRoles(@Param("pseudo") String pseudo);

    @Query( "FROM Utilisateur u " +
            "WHERE u.pseudo = :pseudo " +
            "ORDER BY u.id DESC")
    Optional<Utilisateur> trouverParPseudo(@Param("pseudo") String pseudo);
}
