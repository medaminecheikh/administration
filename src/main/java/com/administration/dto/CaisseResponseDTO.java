package com.administration.dto;

import lombok.Data;

@Data
public class CaisseResponseDTO {

    private String idCaisse;
    private int numCaise;
    private String f_Actif;
    private UtilisateurUpdateDTO login;
    private EttUpdateDTO cod_ett;
}
