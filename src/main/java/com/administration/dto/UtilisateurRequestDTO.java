package com.administration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurRequestDTO {

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
}
