package com.example.cardbordcollector.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="TBL_CCG")
@Data
@Getter
@Setter
public class Collection {

    @Id
    @GeneratedValue
    @Column(name="collectionId")
    private Long id;

    @Column(name="ccgName")
    private String ccgName;

    @OneToMany
    private List<Card> listCard;

}
