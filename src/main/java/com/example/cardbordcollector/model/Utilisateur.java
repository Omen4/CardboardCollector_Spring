package com.example.cardbordcollector.model;

import com.example.cardbordcollector.view.VueUtilisateur;
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
public class Utilisateur {

    public Utilisateur() {
    }

    public Utilisateur(Integer id) {
        this.id = id;

    }

    @Id
    @Column(name="userid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(VueUtilisateur.Standard.class)
    private int id;

    @Column(name="userpseudo", nullable = false)
    @JsonView(VueUtilisateur.Standard.class)
    private String pseudo;

    private String password;

//    @ManyToMany
//    @JoinTable(
//            name = "utilisateur_role",
//            joinColumns = @JoinColumn(name = "utilisateur_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
//    private Set<Role> listeRole = new HashSet<>();

    @OneToMany
    @JsonView(VueUtilisateur.Standard.class)
    @JoinTable(
            name = "user_collection",
            joinColumns = @JoinColumn(name = "userid", referencedColumnName = "userid"),
            inverseJoinColumns = @JoinColumn(name = "collectionid", referencedColumnName = "collectionid"))
    private List<Collection> listCollection;

    @ManyToMany
    @JsonView(VueUtilisateur.Standard.class)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "userid", referencedColumnName = "userid"),
            inverseJoinColumns = @JoinColumn(name = "roleid", referencedColumnName = "roleid"))
    private Set<Role> listeRole = new HashSet<>();
    
    
//    public String getPassword() {return password;}
//    public void setPassword(String password)   {this.password=password;}
}
