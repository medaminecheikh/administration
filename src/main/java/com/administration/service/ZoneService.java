package com.administration.service;

import com.administration.dto.ZoneRequestDTO;
import com.administration.dto.ZoneResponseDTO;
import com.administration.dto.ZoneUpdateDTO;

import java.util.List;

public interface ZoneService {

    ZoneResponseDTO addZone(ZoneRequestDTO RequestDTO);
    ZoneResponseDTO getZone(String id);
    List<ZoneResponseDTO> listZones();
    void updateZoneDTO(ZoneUpdateDTO dto);
}
