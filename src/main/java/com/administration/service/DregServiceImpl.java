package com.administration.service;

import com.administration.dto.DregionalRequestDTO;
import com.administration.dto.DregionalResponseDTO;
import com.administration.dto.DregionalUpdateDTO;
import com.administration.entity.Dregional;
import com.administration.entity.Ett;
import com.administration.mappers.DregionaleMapper;
import com.administration.repo.DregionalRepo;
import com.administration.repo.EttRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DregServiceImpl implements DregService{

    DregionalRepo dregionalRepo;
    DregionaleMapper dregionaleMapper;
    EttRepo ettRepo;

    public DregServiceImpl(DregionalRepo dregionalRepo, DregionaleMapper dregionaleMapper, EttRepo ettRepo) {
        this.dregionalRepo = dregionalRepo;
        this.dregionaleMapper = dregionaleMapper;
        this.ettRepo = ettRepo;
    }

    @Override
    public DregionalResponseDTO addDreg(DregionalRequestDTO dregionalRequestDTO) {
        Dregional dregional= dregionaleMapper.DregionaleRequestDTODregionale(dregionalRequestDTO);
        dregional.setIdDregional(UUID.randomUUID().toString());
        Dregional dregionalsave=dregionalRepo.save(dregional);
        DregionalResponseDTO dregionalResponseDTO=dregionaleMapper.DregionaleTODregionaleResponseDTO(dregionalsave);

        return dregionalResponseDTO;
    }

    @Override
    public DregionalResponseDTO getDregional(String id) {
        Dregional dregional=dregionalRepo.findById(id).get();
        DregionalResponseDTO dregionalResponseDTO=dregionaleMapper.DregionaleTODregionaleResponseDTO(dregional);
        return dregionalResponseDTO;
    }

    @Override
    public List<DregionalResponseDTO> listDregionals() {
        List<Dregional> dregionals=dregionalRepo.findAll();
        List<DregionalResponseDTO> dregionalResponseDTOS=dregionals.stream()
                .map(dto->dregionaleMapper.DregionaleTODregionaleResponseDTO(dto))
                .collect(Collectors.toList());
        return dregionalResponseDTOS;
    }

    @Override
    public void updateDregionalDTO(DregionalUpdateDTO dto) {
        Dregional dregional=dregionalRepo.findById(dto.getIdDregional()).get();
        dregionaleMapper.updateDregionaleFromDto(dto,dregional);
        dregionalRepo.save(dregional);

    }

    @Override
    public void affecterEttToDreg(String idEtt, String idDreg) {
        Ett ett=ettRepo.findById(idEtt).get();
        Dregional dregional=dregionalRepo.findById(idDreg).get();
        dregional.getEtts().add(ett);
        dregionalRepo.save(dregional);
    }
}
