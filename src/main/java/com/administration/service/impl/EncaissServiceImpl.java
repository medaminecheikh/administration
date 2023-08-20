package com.administration.service.impl;

import com.administration.dto.EncaissResponseDTO;
import com.administration.dto.EncaissUpdateDTO;
import com.administration.entity.*;
import com.administration.repo.*;
import com.administration.service.IEncaissService;
import com.administration.service.mappers.EncaissMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class EncaissServiceImpl implements IEncaissService {
    EncaissMapper encaissMapper;
    EncaissRepo encaissRepo;
    UtilisateurRepo utilisateurRepo;
    FactureRepo factureRepo;
    CaisseRepo caisseRepo;
    EttRepo ettRepo;

    @Override
    public Encaissement addEncaiss(Encaissement encaissement) {
        encaissement.setIdEncaissement(UUID.randomUUID().toString());
        encaissement.setDateEnc(new Date());
        encaissRepo.save(encaissement);
        return encaissement;
    }

    @Override
    public EncaissResponseDTO getEncaissById(String id) {
        Encaissement encaissement = encaissRepo.findById(id).orElse(null);
        if (encaissement != null) {
            return encaissMapper.EncaissTOEncaissResponseDTO(encaissement);
        } else {
            return null;
        }
    }

    @Override
    public List<EncaissResponseDTO> getEncaissementByFacture(String idFact) {
        InfoFacture facture = factureRepo.findById(idFact).orElse(null);

        if (facture != null) {
            List<Encaissement> encaissements = facture.getEncaissements();
            if (encaissements != null) {
                List<EncaissResponseDTO> encaissResponseDTOs = new ArrayList<>();
                encaissements.forEach(encaissement -> {
                    EncaissResponseDTO responseDTO = encaissMapper.EncaissTOEncaissResponseDTO(encaissement);
                    encaissResponseDTOs.add(responseDTO);
                });
                return encaissResponseDTOs;
            }
        }

        return Collections.emptyList();
    }

    @Override
    public List<Encaissement> getEncaissementByUser(String idUser) {
       /* Utilisateur utilisateur = utilisateurRepo.findById(idUser).orElse(null);
        List<Encaissement> encaissements = new ArrayList<>();

        if (utilisateur != null) {
            List<InfoFacture> factures = utilisateur.getFactures();
            for (InfoFacture facture : factures) {
                List<Encaissement> factureEncaissements = facture.getEncaissements();
                encaissements.addAll(factureEncaissements);
            }
        }

        return encaissements;*/
        return null;
    }

    @Override
    public List<EncaissResponseDTO> getEncaissementByCaisse(String idCaisse) {
        Caisse caisse = caisseRepo.findById(idCaisse).orElse(null);
        if (caisse != null) {
            return caisse.getEncaissements().stream().map(
                    encaissement -> encaissMapper.EncaissTOEncaissResponseDTO(encaissement)

            ).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public void updateEncaisse(EncaissUpdateDTO dto) {
        Encaissement encaissement = encaissRepo.findById(dto.getIdEncaissement()).orElse(null);
        if (encaissement != null) {
            encaissMapper.updateEncaissFromDto(dto,encaissement);
            encaissRepo.save(encaissement);
        }
    }

    @Override
    public void deleteEncaisse(String idEncaiss) {
        encaissRepo.deleteById(idEncaiss);
    }


    @Override
    public void affectEncaisseToCaisse(String idEncaiss, String idcai) {
        Encaissement encaissement = encaissRepo.findById(idEncaiss).get();
        Caisse caisse = caisseRepo.findById(idcai).get();
        encaissement.setCaisse(caisse);
        encaissRepo.save(encaissement);
    }


    @Override
    public void affectEncaisseToUser(String idEncaiss, String idUser) {
        Encaissement encaissement = encaissRepo.findById(idEncaiss).get();
        Utilisateur utilisateur = utilisateurRepo.findById(idUser).get();
        encaissement.setUser(utilisateur);
        encaissRepo.save(encaissement);
    }

    @Override
    public void affectAll(String idEncaiss, String idUser, String idcai) {
        Encaissement encaissement = encaissRepo.findById(idEncaiss).get();
        Utilisateur utilisateur = utilisateurRepo.findById(idUser).get();
        Caisse caisse = caisseRepo.findById(idcai).get();
        encaissement.setUser(utilisateur);
        encaissement.setCaisse(caisse);
        encaissRepo.save(encaissement);
    }
}
