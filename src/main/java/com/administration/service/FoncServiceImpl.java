package com.administration.service;

import com.administration.dto.FoncRequestDTO;
import com.administration.dto.FoncResponseDTO;
import com.administration.dto.FoncUpdateDTO;
import com.administration.entity.Fonctionalite;
import com.administration.mappers.FoncMapper;
import com.administration.repo.FoncRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FoncServiceImpl implements FoncService{
    FoncRepo foncRepo;
     FoncMapper foncMapper;

    public FoncServiceImpl(FoncRepo foncRepo, FoncMapper foncMapper) {
        this.foncRepo = foncRepo;
        this.foncMapper = foncMapper;
    }


    @Override
    public FoncResponseDTO addFonc(FoncRequestDTO RequestDTO) {
        Fonctionalite fonctionalite= foncMapper.FonctionaliteRequestDTOFonctionalite(RequestDTO);
        fonctionalite.setCodF(UUID.randomUUID().toString());
        foncRepo.save(fonctionalite);
        FoncResponseDTO foncResponseDTO=foncMapper.FonctionaliteTOFonctionaliteResponseDTO(fonctionalite);
        return foncResponseDTO;
    }

    @Override
    public FoncResponseDTO getFonc(String id) {
        Fonctionalite fonctionalite=foncRepo.findById(id).get();
        FoncResponseDTO foncResponseDTO=foncMapper.FonctionaliteTOFonctionaliteResponseDTO(fonctionalite);
        return foncResponseDTO;
    }

    @Override
    public List<FoncResponseDTO> listFoncs() {
        List<Fonctionalite> fonctionalites=foncRepo.findAll();
        List<FoncResponseDTO> foncResponseDTOS=fonctionalites.stream()
                .map(fonctionalite -> foncMapper.FonctionaliteTOFonctionaliteResponseDTO(fonctionalite))
                .collect(Collectors.toList());
        return foncResponseDTOS;
    }

    @Override
    public void updateFoncDTO(FoncUpdateDTO dto) {
        Fonctionalite fonctionalite=foncRepo.findById(dto.getCodF()).get();
        foncMapper.updateFonctionaliteFromDto(dto,fonctionalite);
        foncRepo.save(fonctionalite);

    }
}
