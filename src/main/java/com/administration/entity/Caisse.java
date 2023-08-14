package com.administration.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "POS_Caisse")
public class Caisse implements Serializable {
    @Id
    private String idCaisse;
    private int numCaise;
    private String f_Actif;
    @OneToOne(mappedBy = "caisse")
    private Utilisateur login;
    @ManyToOne
    private Ett cod_ett;
    @OneToMany(mappedBy = "caisse")
    private List<Encaissement> encaissements;
}
