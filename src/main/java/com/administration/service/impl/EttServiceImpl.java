package com.administration.service.impl;

import com.administration.dto.EttRequestDTO;
import com.administration.dto.EttResponseDTO;
import com.administration.dto.EttUpdateDTO;
import com.administration.entity.Dregional;
import com.administration.entity.Ett;
import com.administration.entity.Zone;
import com.administration.service.mappers.EttMapper;
import com.administration.repo.DregionalRepo;
import com.administration.repo.EttRepo;
import com.administration.repo.UtilisateurRepo;
import com.administration.repo.ZoneRepo;
import com.administration.service.IEttService;
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
public class EttServiceImpl implements IEttService {
    EttRepo ettRepo;
    EttMapper ettMapper;
    UtilisateurRepo utilisateurRepo;
    ZoneRepo zoneRepo;
    DregionalRepo dregionalRepo;



    @Override
    public EttResponseDTO addEtt(EttRequestDTO ettRequestDTO) {
        Ett ett=ettMapper.EttRequestDTOEtt(ettRequestDTO);
        ett.setIdEtt(UUID.randomUUID().toString());
        ettRepo.save(ett);
        return ettMapper.EttTOEttResponseDTO(ett);
    }

    @Override
    public EttResponseDTO getEtt(String id) {
        Ett ett=ettRepo.findById(id).get();
        return ettMapper.EttTOEttResponseDTO(ett);
    }

    @Override
    public List<EttResponseDTO> listEtts() {
        List<Ett> etts=ettRepo.findAll();
        return etts.stream()
                .map(ett -> ettMapper.EttTOEttResponseDTO(ett))
                .collect(Collectors.toList());
    }

    @Override
    public void updateEttDTO(EttUpdateDTO dto) {
        Ett ett=ettRepo.findById(dto.getIdEtt()).get();
        ettMapper.updateEttFromDto(dto,ett);
        ettRepo.save(ett);

    }

    @Override
    public void affecterEttToZone(String idEtt, String idZone) {
        Zone zone =zoneRepo.findById(idZone).get();
        Ett ett=ettRepo.findById(idEtt).get();
        ett.setZone(zone);
        ettRepo.save(ett);
    }

    @Override
    public void removeZone(String idEtt) {
        Ett ett=ettRepo.findById(idEtt).get();
        ett.setZone(null);
        ettRepo.save(ett);
    }

    @Override
        public void affecterEttToDreg(String idEtt, String idDreg) {
            Ett ett=ettRepo.findById(idEtt).get();
            Dregional dregional=dregionalRepo.findById(idDreg).get();
            ett.setDregional(dregional);
            ettRepo.save(ett);
        }

    @Override
    public void deleteEtt(String idEtt) {
        Ett ett=ettRepo.findById(idEtt).get();
        if (ett.getZone()==null&&ett.getDregional()==null)
        {
            ettRepo.deleteById(idEtt);
        }else  throw  new RuntimeException("This Ett with address "+ett.getAdr()+" has associations");
    }

    @Override
    public List<EttResponseDTO> getEttsByDrId(String drId) {
        Dregional dregional = dregionalRepo.findById(drId).orElse(null);
        if (dregional != null) {
            return dregional.getEtts().stream()
                    .map(ettMapper::EttTOEttResponseDTO)
                    .collect(Collectors.toList());

        } else {
            return Collections.emptyList();
        }
    }
}
