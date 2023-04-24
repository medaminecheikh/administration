package com.administration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoncRequestDTO {
    private String codF;
    private String fon_COD_F;
    private String desF;
    private String nomF;
    private String nomMENU;
    private int F_DROIT_ACCES;
    private int F_ADM;

}
