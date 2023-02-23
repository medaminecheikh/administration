package com.administration.service;

import com.administration.dto.EttRequestDTO;
import com.administration.dto.EttResponseDTO;
import com.administration.dto.EttUpdateDTO;
import com.administration.entity.Dregional;
import com.administration.entity.Ett;
import com.administration.entity.Zone;
import com.administration.mappers.EttMapper;
import com.administration.repo.DregionalRepo;
import com.administration.repo.EttRepo;
import com.administration.repo.UtilisateurRepo;
import com.administration.repo.ZoneRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EttServiceImpl implements IEttService {
    EttRepo ettRepo;
    EttMapper ettMapper;
    UtilisateurRepo utilisateurRepo;
    ZoneRepo zoneRepo;
    DregionalRepo dregionalRepo;

    public EttServiceImpl(EttRepo ettRepo, EttMapper ettMapper, UtilisateurRepo utilisateurRepo, ZoneRepo zoneRepo, DregionalRepo dregionalRepo) {
        this.ettRepo = ettRepo;
        this.ettMapper = ettMapper;
        this.utilisateurRepo = utilisateurRepo;
        this.zoneRepo = zoneRepo;
        this.dregionalRepo = dregionalRepo;
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

}
