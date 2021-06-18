package com.example.cardbordcollector.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="TBL_SETS")
@Data
@RequestMapping("/myapi/sets")
public class YgoSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="set_name")
    private String set_name;

    @Column(name="set_code")
    private String set_code;

    @Column(name="set_rarity")
    private String set_rarity;
}
