package com.example.cardbordcollector.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.JSONObject;
import org.springframework.lang.Nullable;


import javax.persistence.*;
import java.util.ArrayList;


@Entity
@Data
public class Card {

    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty("id")
    private long ygoId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("desc")
    private String desc;

    @Nullable
    @JsonProperty("atk")
    private int atk;

    @Nullable
    @JsonProperty("def")
    private int def;

    @JsonProperty("level")
    private int level;

    @JsonProperty("race")
    private String race;

    @JsonProperty("attribute")
    private String attribute;
    //toUpperCase

    @JsonProperty("archetype")
    private String archetype;

    @JsonProperty("scale")
    private int scale;

    @JsonProperty("linkval")
    private int linkval;

    @JsonProperty("linkmarkers")
    private String linkmarkers;

    @JsonProperty("card_sets")
    @OneToMany
    private ArrayList<Card> card_sets;

    @JsonProperty("set_name")
    private String set_name;

    @JsonProperty("set_code")
    private String set_code;

    @JsonProperty("set_rarity")
    private String set_rarity;

    @JsonProperty("set_price")
    private float set_price;

    @JsonProperty("card_images")
    @OneToMany
    private ArrayList<Card> card_images;

    @JsonProperty("image_url")
    private String image_url;

    @JsonProperty("tcg_date")
    private String tcg_date;

    @JsonProperty("ocg_date")
    private String ocg_date;
}
