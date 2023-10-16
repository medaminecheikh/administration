package com.administration.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class InfoFacture implements Serializable {
    @Id
    private String idFacture;
    private String refFacture;
    private String produit;
    private double montant;
    private double solde;
    private int nAppel;
    private String codeClient;
    private String compteFacturation;
    private String typeIdent;
    private String identifiant;
    @Temporal(TemporalType.DATE)
    private Date datLimPai;
    @Temporal(TemporalType.DATE)
    private Date datCreation;
    private String periode;
    @OneToMany(mappedBy = "facture",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Encaissement> encaissements;

}
