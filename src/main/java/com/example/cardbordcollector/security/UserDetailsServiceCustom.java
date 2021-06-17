package com.example.cardbordcollector.security;

import com.example.cardbordcollector.dao.UserDao;
import com.example.cardbordcollector.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceCustom implements UserDetailsService {

    private final UserDao utilisateurDao;
    private Object User;

    public UserDetailsServiceCustom(UserDao utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }

    @Override
    public UserDetailsCustom loadUserByUsername(String pseudoSaisi) throws UsernameNotFoundException {

        User utilisateur = utilisateurDao
                .trouverParPseusoAvecRoles(pseudoSaisi)
                .orElseThrow(() -> new UsernameNotFoundException(pseudoSaisi + " inconnu"));

        return new UserDetailsCustom((com.example.cardbordcollector.model.User) User);
    }
}
