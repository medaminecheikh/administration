package com.administration.dto;

import com.administration.entity.CategProfil;
import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
public class ProfilResponseDTO {
    private String idProfil;
    private String nomP;
    private String Des_P;
    private CategProfilUpdateDTO categProfil;
    private ModelUpdateDTO model;
    private List<FoncUpdateDTO> functions;
    private List<ProfilUserViewProfileDTO> profilUsers;
}
