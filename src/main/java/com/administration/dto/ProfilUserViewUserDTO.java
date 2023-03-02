package com.administration.dto;

import lombok.Data;

@Data
public class ProfilUserViewUserDTO {
    private Long id;
    private ProfilUpdateDTO profil;
    private String nom_P;
    private String cod_CFRX;
    private String cod_ZONE          ;
    private String cod_DR            ;
    private String cod_ETT       ;
    private String mis_P             ;
    private String categ_PROFIL      ;
}
