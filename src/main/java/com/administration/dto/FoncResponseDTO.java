package com.administration.dto;

import com.administration.entity.Model;
import com.administration.entity.Profile;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
public class FoncResponseDTO {
    private String codF;
    private String FON_COD_F;
    private String desF;
    private String nomF;
    private String nomMENU;
    private int F_DROIT_ACCES;
    private int F_ADM;
    private List<ModelUpdateDTO> models;
    private List<ProfileUpdateDTO> profiles;
}
