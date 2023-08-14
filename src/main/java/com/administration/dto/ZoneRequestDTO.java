package com.administration.dto;

import com.administration.entity.Dregional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZoneRequestDTO {
    private String idZone ;
    private String COD_ZONE ;
    private String DES_ZONE   ;
    private String DES_ZONE_AR;

}
