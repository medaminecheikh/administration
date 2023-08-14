package com.administration.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Encaissement implements Serializable {
    @Id
    private String idEncaissement;
    @Temporal(TemporalType.DATE)
    private Date dateEnc;
    private double montantEnc;
    private String etatEncaissement;
    private String numRecu;
    private String refFacture;
    private int nAppel;
    private String codeClient;
    private String compteFacturation;
    private String typeIdent;
    private String identifiant;
    private String periode;
    private String produit;
    private String modePaiement;
    private String numCheq;
    private String rib;
    private String banque;
    private String agenceBQ;
    private String nTransTPE;
    private String refBordereau;

    @ManyToOne
    private Utilisateur user;
    @ManyToOne
    private Caisse caisse;
    @ManyToOne
    private InfoFacture facture;
}
