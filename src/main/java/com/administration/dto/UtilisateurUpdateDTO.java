package com.administration.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class UtilisateurUpdateDTO {
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
    private Date date_CREATION ;
    private int is_EXPIRED ;
    private Date date_EXPIRED ;
    private List<ProfilUserViewUserDTO> profilUser;
    private CaisseUpdateDTO caisse;
}
