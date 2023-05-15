package com.administration.service.impl;

import com.administration.dto.CaisseRequestDTO;
import com.administration.dto.CaisseResponseDTO;
import com.administration.dto.CaisseUpdateDTO;
import com.administration.entity.Caisse;
import com.administration.entity.Ett;
import com.administration.entity.Utilisateur;
import com.administration.repo.CaisseRepo;
import com.administration.repo.EttRepo;
import com.administration.repo.UtilisateurRepo;
import com.administration.service.ICaisseService;
import com.administration.service.mappers.CaisseMappers;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class CaisseServiceImpl implements ICaisseService {

    CaisseRepo caisseRepo;
    CaisseMappers caisseMapper;
    UtilisateurRepo utilisateurRepo;
    EttRepo ettRepo;
    @Override
    public CaisseResponseDTO addCaisse(CaisseRequestDTO caisseRequestDTO) {
        Caisse caisse= caisseMapper.CaisseRequestDTOCaisse(caisseRequestDTO);
        caisse.setIdCaisse(UUID.randomUUID().toString());
        caisseRepo.save(caisse);
        return caisseMapper.CaisseTOCaisseResponseDTO(caisse);
    }

    @Override
    public CaisseResponseDTO getCaisse(String id) {
        Caisse caisse=caisseRepo.findById(id).get();
        return caisseMapper.CaisseTOCaisseResponseDTO(caisse);
    }

    @Override
    public List<CaisseResponseDTO> listCaisses() {
        List<Caisse> caisses=caisseRepo.findAll();
        return caisses.stream().map(caisse -> caisseMapper.CaisseTOCaisseResponseDTO(caisse))
                .collect(Collectors.toList());
    }

    @Override
    public void updateCaisseDTO(CaisseUpdateDTO dto) {
        Caisse caisse=caisseRepo.findById(dto.getIdCaisse()).get();
        caisseMapper.updateCaisseFromDto(dto,caisse);
        caisseRepo.save(caisse);
    }

    @Override
    public void affecterCaisseToUser(String idCaisse, String idUser) {
        Caisse caisse=caisseRepo.findById(idCaisse).get();
        Utilisateur utilisateur=utilisateurRepo.findById(idUser).get();
        caisse.setLogin(utilisateur);
        caisseRepo.save(caisse);
    }

    @Override
    public void removeUser(String idCaisse) {
        Caisse caisse=caisseRepo.findById(idCaisse).get();
        caisse.setLogin(null);
        caisseRepo.save(caisse);
    }

    @Override
    public void affecterCaisseToEtt(String idCaisse, String idEtt) {
        Caisse caisse=caisseRepo.findById(idCaisse).get();
        Ett ett=ettRepo.findById(idEtt).get();
        caisse.setCod_ett(ett);
        caisseRepo.save(caisse);
    }

    @Override
    public void deleteCaisse(String idCaisse) {
        caisseRepo.deleteById(idCaisse);
    }

    @Override
    public List<CaisseResponseDTO> getCaissesByEttId(String Id) {
       Ett ett=ettRepo.findById(Id).get();
        List<Caisse> caisseList=ett.getCaisses();
        return caisseList.stream().map(caisse -> caisseMapper.CaisseTOCaisseResponseDTO(caisse)).
                collect(Collectors.toList());
    }
}
