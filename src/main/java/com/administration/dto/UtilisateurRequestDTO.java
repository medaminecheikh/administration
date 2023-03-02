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
    private String confirmedpassword;
    private String prenU;
    private String descU;
    private int estActif;
    private int f_ADM_LOC;
    private int f_ADM_CEN;
    private String matricule;
    private Date date_CREATION ;
    private int is_EXPIRED ;
    private Date date_EXPIRED ;
}
