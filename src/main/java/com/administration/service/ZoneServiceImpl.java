package com.administration.service;

import com.administration.dto.ZoneRequestDTO;
import com.administration.dto.ZoneResponseDTO;
import com.administration.dto.ZoneUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneServiceImpl implements ZoneService{
    @Override
    public ZoneResponseDTO addZone(ZoneRequestDTO RequestDTO) {
        return null;
    }

    @Override
    public ZoneResponseDTO getZone(String id) {
        return null;
    }

    @Override
    public List<ZoneResponseDTO> listZones() {
        return null;
    }

    @Override
    public void updateZoneDTO(ZoneUpdateDTO dto) {

    }
}
