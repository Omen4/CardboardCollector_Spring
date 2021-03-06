package com.example.cardbordcollector.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="SETS")
@Getter
@Setter
//@RequestMapping("/myapi/sets")
public class YgoSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "setid", nullable = false)
    private int setId;

    @Column(name="setname")
    private String set_name;

    @Column(name="setcode")
    private String set_code;

    @Column(name="setrarity")
    private String set_rarity;
}
