package com.example.cardbordcollector.model;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="YGOCARDS")
@Getter
@Setter
public class Card {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cardId")
    private Long cardId;

    @Column(name="ygoId")
    private Long ygoId;

    @Column(name="name")
    private String name;

    @Column(name="type")
    private String type;

    @Column(name="desc")
    private String desc;

    @Column(name="atk")
    private int atk;

    @Column(name="def")
    private int def;

    @Column(name="level")
    private int level;

    @Column(name="race")
    private String race;

    @Column(name="attribute")
    private String attribute;
    //toUpperCase

    @Column(name="archetype")
    private String archetype;

    @Column(name="scale")
    private int scale;

    @Column(name="linkval")
    private int linkval;

    @Column(name="linkmarkers")
    private String linkmarkers;

//    @OneToMany
//    @JoinTable(
//            name = "card_ygosets",
//            joinColumns = @JoinColumn(name = "card_id", referencedColumnName = "card_id"),
//            inverseJoinColumns = @JoinColumn(name = "setId", referencedColumnName = "setId"))
//    private List<YgoSet> listCardSets;

    @Column(name="set_name")
    private String set_name;

    @Column(name="set_code")
    private String set_code;

    @Column(name="set_rarity")
    private String set_rarity;

    @Column(name="set_price")
    private float set_price;

    @Column(name="image_url")
    private String image_url;

    @Column(name="tcg_date")
    private String tcg_date;

    @Column(name="ocg_date")
    private String ocg_date;
}
