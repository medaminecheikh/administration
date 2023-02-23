package com.administration.dto;

import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
public class ProfilResponseDTO {
    private String idProfil;
    private String Nom_P;
    private String Des_P;
    private ModelUpdateDTO model;
    private List<FoncUpdateDTO> functions;
    private List<ProfilUserViewProfileDTO> profilUsers;
}
