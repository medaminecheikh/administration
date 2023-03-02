package com.administration.dto;

import lombok.Data;

@Data
public class ProfilUserViewProfileDTO {
    private Long id;
    private UtilisateurUpdateDTO utilisateur;
    private String nom_P;
    private String cod_CFRX;
    private String cod_ZONE          ;
    private String cod_DR            ;
    private String cod_ETT       ;
    private String mis_P             ;
    private String categ_PROFIL      ;
}
