package com.administration.dto;

import lombok.Data;

import javax.persistence.Column;

@Data

public class ProfilUpdateDTO {
    private String idProfil;
    private String nomP;
    private String Des_P;

}
