package com.administration.dto;

import com.administration.entity.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EttRequestDTO {
    private String idEtt;
    private String des_SRC_ENC;
    private String COD_CFRX;
    private String prfx_SRC_ENC;
    private String adr;
    private int is_BSCS;

}
