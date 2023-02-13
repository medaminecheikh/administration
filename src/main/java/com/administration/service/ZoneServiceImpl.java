package com.administration.service;

import com.administration.dto.ZoneRequestDTO;
import com.administration.dto.ZoneResponseDTO;
import com.administration.dto.ZoneUpdateDTO;
import com.administration.repo.ZoneRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneServiceImpl implements ZoneService{
    ZoneRepo zoneRepo;

    public ZoneServiceImpl(ZoneRepo zoneRepo) {
        this.zoneRepo = zoneRepo;
    }

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
