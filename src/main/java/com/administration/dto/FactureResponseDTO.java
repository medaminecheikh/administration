package com.administration.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FactureResponseDTO {
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
    private Date datLimPai;
    private Date datCreation;
    private String periode;
    private List<EncaissUpdateDTO> encaissements;
    private long totalElements;
}
