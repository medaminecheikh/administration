package com.administration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EncaissRequestDTO {
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
}
