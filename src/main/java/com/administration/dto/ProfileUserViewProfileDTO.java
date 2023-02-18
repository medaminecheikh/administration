package com.administration.dto;

import lombok.Data;

@Data
public class ProfileUserViewProfileDTO {
    private Long id;
    private UtilisateurUpdateDTO utilisateur;
    private String COD_ZONE          ;
    private String COD_DR            ;
    private String COD_ETT       ;
    private String MIS_P             ;
    private String CATEG_PROFIL      ;
}
