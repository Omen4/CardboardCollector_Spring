package com.example.cardbordcollector.model;
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
    private int id;
    
    @Column(name="rolename")
    private String denomination;



}
