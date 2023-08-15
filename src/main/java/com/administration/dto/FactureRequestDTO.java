package com.administration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FactureRequestDTO {
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
    private String periode;
}
