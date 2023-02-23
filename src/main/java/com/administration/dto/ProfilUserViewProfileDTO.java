package com.administration.dto;

import lombok.Data;

@Data
public class ProfilUserViewProfileDTO {
    private Long id;
    private UtilisateurUpdateDTO utilisateur;
    private String nom_P;
    private String COD_CFRX;
    private String COD_ZONE          ;
    private String COD_DR            ;
    private String COD_ETT       ;
    private String MIS_P             ;
    private String CATEG_PROFIL      ;
}
