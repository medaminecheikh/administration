package com.administration.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UtilisateurResponseDTO {
    private String idUser;
    private String login;
    private String nomP;
    private String nomU;
    private String pwdU;
    private String prenU;
    private String descU;
    private int estActif;
    private int f_ADM_LOC;
    private int f_ADM_CEN;
    private String matricule;
    private Date DATE_CREATION ;
    private int IS_EXPIRED ;
    private Date DATE_EXPIRED ;
    private ProfilUserViewUserDTO profilUser;
    private EttUpdateDTO ett;

    private long totalElements;
}
