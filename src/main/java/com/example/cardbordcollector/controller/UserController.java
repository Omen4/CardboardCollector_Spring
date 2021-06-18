package com.example.cardbordcollector.controller;

import com.example.cardbordcollector.dao.UserDao;
import com.example.cardbordcollector.model.Role;
import com.example.cardbordcollector.model.User;
import com.example.cardbordcollector.security.JwtUtil;
import com.example.cardbordcollector.security.UserDetailsCustom;
import com.example.cardbordcollector.security.UserDetailsServiceCustom;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@Data
public class UserController {

    private UserDao utilisateurDao;
    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;
    private UserDetailsServiceCustom userDetailsServiceCustom;
    private PasswordEncoder passwordEncoder;


    @Autowired
    UserController(
            UserDao utilisateurDao,
            JwtUtil jwtUtil,
            AuthenticationManager authenticationManager,
            UserDetailsServiceCustom userDetailsServiceCustom,
            PasswordEncoder passwordEncoder
    ) {
        this.utilisateurDao = utilisateurDao;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsServiceCustom = userDetailsServiceCustom;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/authentification")
    public ResponseEntity<String> authentification(@RequestBody User utilisateur) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            utilisateur.getPseudo(), utilisateur.getMotDePasse()
                    )
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("Mauvais pseudo / mot de passe");
        }

        UserDetailsCustom userDetails = this.userDetailsServiceCustom.loadUserByUsername(utilisateur.getPseudo());

        return ResponseEntity.ok(jwtUtil.generateToken(userDetails));
    }

    @PostMapping("/inscription")
    public ResponseEntity<String> inscription(@RequestBody User utilisateur) {

        Optional<User> utilisateurDoublon = utilisateurDao.trouverParPseudo(utilisateur.getPseudo());

        if (utilisateurDoublon.isPresent()) {
            return ResponseEntity.badRequest().body("Ce pseudo est déja utilisé");
        } else {

            utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));

            Role roleUtilisateur = new Role();
            roleUtilisateur.setId(1);

            utilisateur.getListeRole().add(roleUtilisateur);

            utilisateurDao.saveAndFlush(utilisateur);

            return ResponseEntity.ok(Integer.toString(utilisateur.getId()));
        }
    }

    @PostMapping("/admin/utilisateur")
    public ResponseEntity<String> updateUser(@RequestBody User utilisateur) {

        Optional<User> utilisateurBddOptional = utilisateurDao.trouverParPseudo(utilisateur.getPseudo());

        if (utilisateurBddOptional.isPresent()) {
            User utilisateurBdd = utilisateurBddOptional.get();
            utilisateur.setMotDePasse(utilisateurBdd.getMotDePasse());
            utilisateurDao.save(utilisateur);
            return ResponseEntity.ok().body("Utilisateur mis à jour");
        }

        return ResponseEntity.notFound().build();
    }


    @GetMapping("/user/utilisateur-connecte")
    public ResponseEntity<User> getInformationUtilisateurConnecte(
            @RequestHeader(value = "Authorization") String authorization) {
        //la valeur du champs authorization est extrait de l'entête de la requête

        //On supprime la partie "Bearer " de la valeur de l'authorization
        String token = authorization.substring(7);

        //on extrait l'information souhaitée du token
        String username = jwtUtil.getTokenBody(token).getSubject();

        Optional<User> utilisateur = utilisateurDao.trouverParPseudo(username);

        if (utilisateur.isPresent()) {
            return ResponseEntity.ok().body(utilisateur.get());
        }

        return ResponseEntity.notFound().build();
    }


    @GetMapping("/admin/utilisateur/{id}")
    public ResponseEntity<User> getUtilisateur(@PathVariable int id) {

        Optional<User> utilisateur = utilisateurDao.findById(id);

        if (utilisateur.isPresent()) {
            return ResponseEntity.ok(utilisateur.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }


    @GetMapping("/admin/utilisateurs")
    public ResponseEntity<List<User>> getUtilisateurs() {

        return ResponseEntity.ok(utilisateurDao.findAll());
    }

    @DeleteMapping("/admin/utilisateur/{id}")
    public ResponseEntity<Integer> deleteUtilisateur(@PathVariable int id) {

        if (utilisateurDao.existsById(id)) {
            utilisateurDao.deleteById(id);
            return ResponseEntity.ok(id);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}






