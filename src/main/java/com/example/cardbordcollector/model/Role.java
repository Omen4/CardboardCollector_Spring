package com.example.cardbordcollector.model;
import com.example.cardbordcollector.view.VueUtilisateur;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="ROLES")
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="roleid")
    @JsonView(VueUtilisateur.Standard.class)
    private int id;
    
    @Column(name="rolename")
    @JsonView(VueUtilisateur.Standard.class)
    private String denomination;



}
