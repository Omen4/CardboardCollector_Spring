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
    @Column(name="cardid")
    private int id;

    @Column(name="ygoid")
    private int ygoid;

    @Column(name="cardname")
    private String name;

    @Column(name="cardtype")
    private String type;

    @Column(name="carddesc")
    private String desc;

    @Column(name="cardatk")
    private int atk;

    @Column(name="carddef")
    private int def;

    @Column(name="cardlevel")
    private int level;

    @Column(name="cardrace")
    private String race;

    @Column(name="cardattribute")
    private String attribute;
    //toUpperCase

    @Column(name="cardarchetype")
    private String archetype;

    @Column(name="cardscale")
    private int scale;

    @Column(name="cardlinkval")
    private int linkval;

    @Column(name="cardlinkmarkers")
    private String linkmarkers;

    @OneToMany
    @JoinTable(
            name = "ygocards_ygosets",
            joinColumns = @JoinColumn(name = "cardid", referencedColumnName = "cardid"),
            inverseJoinColumns = @JoinColumn(name = "setid", referencedColumnName = "setid"))
    private List<YgoSet> listCardSets;

    @Column(name="setname")
    private String set_name;

    @Column(name="setcode")
    private String set_code;

    @Column(name="setrarity")
    private String set_rarity;

    @Column(name="setprice")
    private float set_price;

    @Column(name="imageurl")
    private String image_url;

    @Column(name="tcgdate")
    private String tcg_date;

    @Column(name="ocgdate")
    private String ocg_date;
}
