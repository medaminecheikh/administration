package com.administration.dto;

import com.administration.entity.Dregional;
import com.administration.entity.Ett;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

@Data
public class ZoneResponseDTO {
    private String idZone;
    private String COD_ZONE ;
    private String DES_ZONE   ;
    private String DES_ZONE_AR;
    private List<DregionalResponseDTO> dregionals;
    private long totalElements;
}
