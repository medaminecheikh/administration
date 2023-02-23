package com.administration.dto;

import lombok.Data;

import javax.persistence.Column;

@Data

public class ProfilUpdateDTO {
    private String idProfil;
    private String Nom_P;
    private String Des_P;

}
