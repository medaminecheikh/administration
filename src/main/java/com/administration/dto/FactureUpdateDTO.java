package com.administration.dto;

import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class FactureUpdateDTO {
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
    private String periode;
}
