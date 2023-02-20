package com.administration.service;

import com.administration.dto.ZoneRequestDTO;
import com.administration.dto.ZoneResponseDTO;
import com.administration.dto.ZoneUpdateDTO;
import com.administration.entity.Dregional;
import com.administration.entity.Zone;
import com.administration.mappers.ZoneMapper;
import com.administration.repo.DregionalRepo;
import com.administration.repo.ZoneRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ZoneServiceImpl implements ZoneService{
    ZoneRepo zoneRepo;
    ZoneMapper zoneMapper;
    DregionalRepo dregionalRepo;


    public ZoneServiceImpl(ZoneRepo zoneRepo, ZoneMapper zoneMapper, DregionalRepo dregionalRepo) {
        this.zoneRepo = zoneRepo;
        this.zoneMapper = zoneMapper;
        this.dregionalRepo = dregionalRepo;
    }

    @Override
    public ZoneResponseDTO addZone(ZoneRequestDTO RequestDTO) {
        Zone zone=zoneMapper.ZoneRequestDTOZone(RequestDTO);
        zone.setCodZone(UUID.randomUUID().toString());
        zoneRepo.save(zone);
        ZoneResponseDTO zoneResponseDTO=zoneMapper.ZoneTOZoneResponseDTO(zone);
        return zoneResponseDTO;
    }

    @Override
    public ZoneResponseDTO getZone(String id) {
        Zone zone=zoneRepo.findById(id).get();
        ZoneResponseDTO zoneResponseDTO=zoneMapper.ZoneTOZoneResponseDTO(zone);
        return zoneResponseDTO;
    }

    @Override
    public List<ZoneResponseDTO> listZones() {
        List<Zone> zones=zoneRepo.findAll();
        List<ZoneResponseDTO> zoneResponseDTOList=zones.stream()
                .map(zone -> zoneMapper.ZoneTOZoneResponseDTO(zone))
                .collect(Collectors.toList());
        return zoneResponseDTOList;
    }

    @Override
    public void updateZoneDTO(ZoneUpdateDTO dto) {
        Zone zone=zoneRepo.findById(dto.getCodZone()).get();
        zoneMapper.updateZoneFromDto(dto,zone);
        zoneRepo.save(zone);

    }

    @Override
    public void affecterDregToZone(String idDreg, String idZone) {
        Dregional dregional=dregionalRepo.findById(idDreg).get();
        Zone zone=zoneRepo.findById(idZone).get();
        dregional.setZone(zone);
        dregionalRepo.save(dregional);
    }

    @Override
    public void removeDreg(String idDreg) {
        Dregional dregional=dregionalRepo.findById(idDreg).get();
        dregional.setZone(null);
        dregionalRepo.save(dregional);
    }

    @Override
    public void deleteZone(String idZone) {
        Zone zone=zoneRepo.findById(idZone).get();
        if (zone.getDregionals()==null&&zone.getEtts()==null)
        {
            zoneRepo.deleteById(idZone);
        }
        else  throw new RuntimeException("This zone with address "+zone.getAdr()+" has associations");
    }
}
