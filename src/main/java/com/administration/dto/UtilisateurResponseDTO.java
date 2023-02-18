package com.administration.dto;

import lombok.Data;

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
    private ProfileUserViewUserDTO profileUser;
    private EttUpdateDTO ett;
}
