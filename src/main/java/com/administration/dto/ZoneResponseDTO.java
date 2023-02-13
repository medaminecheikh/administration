package com.administration.dto;

import com.administration.entity.Dregional;
import lombok.Data;

import javax.persistence.OneToMany;
import java.util.List;

@Data
public class ZoneResponseDTO {
    private String idZone;
    private String nomZone;
    private int nbrVille;

    private List<DregionalResponseDTO> dregionals;
}
