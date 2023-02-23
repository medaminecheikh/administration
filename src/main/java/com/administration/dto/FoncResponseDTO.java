package com.administration.dto;

import lombok.Data;

import java.util.List;

@Data
public class FoncResponseDTO {
    private String idFonc;
    private String codF;
    private String FON_COD_F;
    private String desF;
    private String nomF;
    private String nomMENU;
    private int F_DROIT_ACCES;
    private int F_ADM;
    private List<ModelUpdateDTO> models;
    private List<ProfilUpdateDTO> profils;
}
