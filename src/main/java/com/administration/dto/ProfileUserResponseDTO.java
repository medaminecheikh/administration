package com.administration.dto;

import com.administration.entity.Profile;
import com.administration.entity.Utilisateur;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
public class ProfileUserResponseDTO {
    private Long id;
    private ProfileUpdateDTO profile;
    private String COD_CFRX          ;
    private String COD_DR            ;
    private String COD_SRC_ENC       ;
    private String MIS_P             ;
    private String CATEG_PROFIL      ;
}
