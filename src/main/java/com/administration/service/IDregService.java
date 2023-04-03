package com.administration.service;

import com.administration.dto.DregionalRequestDTO;
import com.administration.dto.DregionalResponseDTO;
import com.administration.dto.DregionalUpdateDTO;

import java.util.List;

public interface IDregService {

    DregionalResponseDTO addDreg(DregionalRequestDTO dregionalRequestDTO);
    DregionalResponseDTO getDregional(String id);
    List<DregionalResponseDTO> listDregionals();
    void updateDregionalDTO(DregionalUpdateDTO dto);
    List<DregionalResponseDTO> getDregionalsByZoneId(String zoneId);


}
