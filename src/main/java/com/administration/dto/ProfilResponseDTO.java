package com.administration.dto;

import com.administration.entity.CategProfil;
import lombok.Data;

import javax.persistence.Column;
import java.util.List;
import java.util.Set;

@Data
public class ProfilResponseDTO {
    private String idProfil;
    private String nomP;
    private String Des_P;
    private CategProfilUpdateDTO categProfil;
    private ModelUpdateDTO model;
    private Set<FoncUpdateDTO> fonctions;
    private List<ProfilUserViewProfileDTO> profilUsers;
    private long totalElements;
}
