package com.administration.service.impl;

import com.administration.dto.DregionalRequestDTO;
import com.administration.dto.DregionalResponseDTO;
import com.administration.dto.DregionalUpdateDTO;
import com.administration.entity.Dregional;
import com.administration.entity.Zone;
import com.administration.service.mappers.DregionaleMapper;
import com.administration.repo.DregionalRepo;
import com.administration.repo.EttRepo;
import com.administration.repo.ZoneRepo;
import com.administration.service.IDregService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class DregServiceImpl implements IDregService {

    DregionalRepo dregionalRepo;
    DregionaleMapper dregionaleMapper;
    EttRepo ettRepo;
    ZoneRepo zoneRepo;


    @Override
    public DregionalResponseDTO addDreg(DregionalRequestDTO dregionalRequestDTO) {
        Dregional dregional= dregionaleMapper.DregionaleRequestDTODregionale(dregionalRequestDTO);
        Dregional dregionalsave=dregionalRepo.save(dregional);

        return dregionaleMapper.DregionaleTODregionaleResponseDTO(dregionalsave);
    }

    @Override
    public DregionalResponseDTO getDregional(String id) {
        Dregional dregional=dregionalRepo.findById(id).orElse(null);
        if (dregional!=null) {
            return dregionaleMapper.DregionaleTODregionaleResponseDTO(dregional);
        } else {
            return null;
        }
    }

    @Override
    public List<DregionalResponseDTO> listDregionals() {
        List<Dregional> dregionals=dregionalRepo.findAll();
        return dregionals.stream()
                .map(dto->dregionaleMapper.DregionaleTODregionaleResponseDTO(dto))
                .collect(Collectors.toList());
    }

    @Override
    public void updateDregionalDTO(DregionalUpdateDTO dto) {
        Dregional dregional=dregionalRepo.findById(dto.getIdDr()).get();
        dregionaleMapper.updateDregionaleFromDto(dto,dregional);
        dregionalRepo.save(dregional);

    }

    @Override
    public List<DregionalResponseDTO> getDregionalsByZoneId(String zoneId) {
        Zone zone = zoneRepo.findById(zoneId).orElse(null);
        if (zone != null) {
            return zone.getDregionals().stream()
                    .map(dregionaleMapper::DregionaleTODregionaleResponseDTO)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }


}
