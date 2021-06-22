package com.example.cardbordcollector.model;

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
    @Column(name="collectionId")
    private Long id;

    @Column(name="ccgName")
    private String ccgName;

//    @OneToMany
//    @JoinTable(
//            name = "collection_card",
//            joinColumns = @JoinColumn(name = "collectionId", referencedColumnName = "collectionId"),
//            inverseJoinColumns = @JoinColumn(name = "card_id", referencedColumnName = "card_id"))
//    private List<Card> listCard;

}
