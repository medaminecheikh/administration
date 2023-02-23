package com.administration.service;

import com.administration.dto.ZoneRequestDTO;
import com.administration.dto.ZoneResponseDTO;
import com.administration.dto.ZoneUpdateDTO;

import java.util.List;

public interface IZoneService {

    ZoneResponseDTO addZone(ZoneRequestDTO RequestDTO);
    ZoneResponseDTO getZone(String id);
    List<ZoneResponseDTO> listZones();
    void updateZoneDTO(ZoneUpdateDTO dto);
    void affecterDregToZone(String idDreg,String idZone);
    void removeDreg(String idDreg);
    void deleteZone(String idZone);
}
