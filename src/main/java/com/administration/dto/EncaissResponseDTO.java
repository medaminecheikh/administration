package com.administration.dto;

import com.administration.entity.Caisse;
import com.administration.entity.InfoFacture;
import com.administration.entity.Utilisateur;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class EncaissResponseDTO {

    private String idEncaissement;
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
    private long totalElements;
    private UtilisateurUpdateDTO user;

    private CaisseUpdateDTO caisse;

    private FactureUpdateDTO facture;
}
