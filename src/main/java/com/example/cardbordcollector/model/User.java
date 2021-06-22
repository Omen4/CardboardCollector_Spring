package com.example.cardbordcollector.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="USERS")
@Getter
@Setter
public class User {

    @Id
    @Column(name="userid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="userpseudo")
    private String pseudo;

    @Column(name="userpassword")
    private String password;

//    @ManyToMany
//    @JoinTable(
//            name = "utilisateur_role",
//            joinColumns = @JoinColumn(name = "utilisateur_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
//    private Set<Role> listeRole = new HashSet<>();

    @OneToMany
    @JoinTable(
            name = "user_collection",
            joinColumns = @JoinColumn(name = "userid", referencedColumnName = "userid"),
            inverseJoinColumns = @JoinColumn(name = "collectionid", referencedColumnName = "collectionid"))
    private List<Collection> listCollection;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "userid", referencedColumnName = "userid"),
            inverseJoinColumns = @JoinColumn(name = "roleid", referencedColumnName = "roleid"))
    private Set<Role> listeRole = new HashSet<>();
}
