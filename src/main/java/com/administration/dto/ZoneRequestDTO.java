package com.administration.dto;

import com.administration.entity.Dregional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZoneRequestDTO {

    private String nomZone;
    private int nbrVille;

}
