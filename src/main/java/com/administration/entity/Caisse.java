package com.administration.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

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
    @OneToOne
    private Utilisateur login;
    @ManyToOne
    private Ett cod_ett;
}
