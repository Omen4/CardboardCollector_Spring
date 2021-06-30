package com.example.cardbordcollector.model;

import com.example.cardbordcollector.view.VueUtilisateur;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="COLLECTION")
@Getter
@Setter
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="collectionid")
    @JsonView(VueUtilisateur.Standard.class)
    private int id;

    @Column(name="ccgname")
    @JsonView(VueUtilisateur.Standard.class)
    private String ccgName;

    @ManyToMany
    @JsonView(VueUtilisateur.Standard.class)
    @JoinTable(
            name = "collection_ygocards",
            joinColumns = @JoinColumn(name = "collectionid", referencedColumnName = "collectionid"),
            inverseJoinColumns = @JoinColumn(name = "cardid", referencedColumnName = "cardid"))
    private List<Card> listCard;

}
