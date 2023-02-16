package com.administration.dto;

import com.administration.entity.Profile;
import lombok.Data;

import java.util.List;

@Data
public class FoncUpdateDTO {
    private String codF;
    private String FON_COD_F;
    private String desF;
    private String nomF;
    private String nomMENU;
    private int F_DROIT_ACCES;
    private int F_ADM;

}
