package com.administration.dto;

import com.administration.entity.Dregional;
import com.administration.entity.Utilisateur;
import com.administration.entity.Zone;
import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
public class EttResponseDTO {
    private String codEtt;
    private String des_SRC_ENC;
    private String prfx_SRC_ENC;
    private String adr;
    private int is_BSCS;
    private List<UtilisateurUpdateDTO> utilisateurs;
    private DregionalUpdateDTO dregional;
    private ZoneUpdateDTO zone;
}
