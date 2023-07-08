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
import java.util.Optional;
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
        Caisse caisse = caisseMapper.CaisseRequestDTOCaisse(caisseRequestDTO);
        caisse.setIdCaisse(UUID.randomUUID().toString());
        caisseRepo.save(caisse);
        return caisseMapper.CaisseTOCaisseResponseDTO(caisse);
    }

    @Override
    public CaisseResponseDTO getCaisse(String id) {
        Caisse caisse = caisseRepo.findById(id).get();
        return caisseMapper.CaisseTOCaisseResponseDTO(caisse);
    }

    @Override
    public List<CaisseResponseDTO> listCaisses() {
        List<Caisse> caisses = caisseRepo.findAll();
        return caisses.stream().map(caisse -> caisseMapper.CaisseTOCaisseResponseDTO(caisse))
                .collect(Collectors.toList());
    }

    @Override
    public void updateCaisseDTO(CaisseUpdateDTO dto) {
        Caisse caisse = caisseRepo.findById(dto.getIdCaisse()).get();
        caisseMapper.updateCaisseFromDto(dto, caisse);
        caisseRepo.save(caisse);
    }

    @Override
    public void affecterCaisseToUser(String idCaisse, String idUser) {
        Caisse caisse = caisseRepo.findById(idCaisse).orElse(null);
        Utilisateur utilisateur = utilisateurRepo.findById(idUser).orElse(null);

        if (caisse != null && utilisateur != null) {
            utilisateur.setCaisse(caisse);
            utilisateurRepo.save(utilisateur);
        } else {
            // Throw an exception or handle the error accordingly
            throw new IllegalArgumentException("Invalid caisse or utilisateur ID");
            // Or you can log an error message and perform alternative actions
            // logger.error("Invalid caisse or utilisateur ID");
            // performAlternativeAction();
        }
    }

    @Override
    public void removeUser(String idUser) {

        Utilisateur utilisateur = utilisateurRepo.findById(idUser).get();
        utilisateur.setCaisse(null);
        utilisateurRepo.save(utilisateur);
    }

    @Override
    public void affecterCaisseToEtt(String idCaisse, String idEtt) {
        Caisse caisse = caisseRepo.findById(idCaisse).get();
        Ett ett = ettRepo.findById(idEtt).get();
        caisse.setCod_ett(ett);
        caisseRepo.save(caisse);
    }

    @Override
    public void deleteCaisse(String idCaisse) {
        Optional<Caisse> caisseOptional = caisseRepo.findById(idCaisse);
        if (caisseOptional.isPresent()) {
            Caisse caisse = caisseOptional.get();

            // Remove the association from the associated Utilisateur entity
            Utilisateur utilisateur = caisse.getLogin();
            if (utilisateur != null) {
                removeUser(utilisateur.getIdUser());
            }
            caisseRepo.deleteById(idCaisse);
        }
    }

    @Override
    public List<CaisseResponseDTO> getCaissesByEttId(String Id) {
        Ett ett = ettRepo.findById(Id).get();
        List<Caisse> caisseList = ett.getCaisses();
        return caisseList.stream().map(caisse -> caisseMapper.CaisseTOCaisseResponseDTO(caisse)).
                collect(Collectors.toList());
    }
}
