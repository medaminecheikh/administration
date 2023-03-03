package com.administration.dto;

import com.administration.entity.Profil;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Data
public class CategProfilUpdateDTO {
    private String idCategProfile;
    private String COD_CATEG_P  ;
    private String DES_CATEG_P;

}
