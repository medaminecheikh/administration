package com.administration.service;

import com.administration.dto.EttRequestDTO;
import com.administration.dto.EttResponseDTO;
import com.administration.dto.EttUpdateDTO;
import com.administration.entity.Ett;
import com.administration.mappers.EttMapper;
import com.administration.repo.EttRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EttServiceImpl implements EttService{
    EttRepo ettRepo;
    EttMapper ettMapper;

    public EttServiceImpl(EttRepo ettRepo, EttMapper ettMapper) {
        this.ettRepo = ettRepo;
        this.ettMapper = ettMapper;
    }



    @Override
    public EttResponseDTO addEtt(EttRequestDTO ettRequestDTO) {
        Ett ett=ettMapper.EttRequestDTOEtt(ettRequestDTO);
        ett.setIdEtt(UUID.randomUUID().toString());
        ettRepo.save(ett);
        EttResponseDTO ettResponseDTO=ettMapper.EttTOEttResponseDTO(ett);
        return ettResponseDTO;
    }

    @Override
    public EttResponseDTO getEtt(String id) {
        Ett ett=ettRepo.findById(id).get();
        EttResponseDTO ettResponseDTO=ettMapper.EttTOEttResponseDTO(ett);
        return ettResponseDTO;
    }

    @Override
    public List<EttResponseDTO> listEtts() {
        List<Ett> etts=ettRepo.findAll();
        List<EttResponseDTO> ettResponseDTOList=etts.stream()
                .map(ett -> ettMapper.EttTOEttResponseDTO(ett))
                .collect(Collectors.toList());
        return ettResponseDTOList;
    }

    @Override
    public void updateEttDTO(EttUpdateDTO dto) {
        Ett ett=ettRepo.findById(dto.getIdEtt()).get();
        ettMapper.updateEttFromDto(dto,ett);
        ettRepo.save(ett);

    }
}
