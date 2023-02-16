package com.administration.dto;

import com.administration.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoncRequestDTO {
    private String FON_COD_F;
    private String desF;
    private String nomF;
    private String nomMENU;
    private int F_DROIT_ACCES;
    private int F_ADM;

}
