package com.administration.service.impl;

import com.administration.dto.EncaissResponseDTO;
import com.administration.dto.EncaissUpdateDTO;
import com.administration.dto.FactureResponseDTO;
import com.administration.entity.*;
import com.administration.repo.*;
import com.administration.service.IEncaissService;
import com.administration.service.mappers.EncaissMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class EncaissServiceImpl implements IEncaissService {
    EncaissMapper encaissMapper;
    EncaissRepo encaissRepo;
    UtilisateurRepo utilisateurRepo;
    FactureRepo factureRepo;
    CaisseRepo caisseRepo;
    EttRepo ettRepo;

    @Override
    public Encaissement addEncaiss(Encaissement encaissement) {

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
    public EncaissResponseDTO affectEncaisseToCaisse(String idEncaiss,String idCai) {
        try {
            Encaissement encaissement = encaissRepo.findById(idEncaiss)
                    .orElseThrow(() -> new EntityNotFoundException("Encaissement not found with id: " + idEncaiss));
            Caisse caisse = caisseRepo.findById(idCai)
                    .orElseThrow(() -> new EntityNotFoundException("Caisse not found with id: " + idCai));

            encaissement.setCaisse(caisse);
            encaissRepo.save(encaissement);

            return encaissMapper.EncaissTOEncaissResponseDTO(encaissement);
        } catch (Exception e) {
            // Log the error for debugging purposes
            log.error("Failed to affect caisse to encaissement: {}", e.getMessage());
            // You might want to throw a custom exception here or handle it in another way.
            throw new RuntimeException("Failed to affect caisse to encaissement: " + e.getMessage());
        }

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

    @Override
    public List<EncaissResponseDTO> getAllEncaissement() {
        List<Encaissement> encaissementList= encaissRepo.findAll();
        return encaissementList.stream().map(encaissement -> encaissMapper.EncaissTOEncaissResponseDTO(encaissement)).collect(Collectors.toList());
    }



    @Override
    public List<EncaissResponseDTO> getEncaissementsForCaisseInCurrentMonth(String caisseId) {
        // Calculate the start and end date of the current month
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date endDate = calendar.getTime();
        List<Encaissement> encaissementList =encaissRepo.findEncaissementsForCaisseInCurrentMonth(caisseId, startDate, endDate);
        // Fetch the list of Encaissements for the specified Caisse within the current month
        return encaissementList.stream().map(encaissement -> encaissMapper.EncaissTOEncaissResponseDTO(encaissement)).collect(Collectors.toList());
    }
    @Override
    public List<EncaissResponseDTO> searchEncaiss(String produit, String identifiant, String modePaiement, String typeIdent, Double montantEnc, String refFacture, PageRequest pageable) {
        Page<Encaissement> encaissements = encaissRepo.searchEncaiss(produit, identifiant, modePaiement, typeIdent, montantEnc, refFacture, pageable);
        long count = encaissements.getTotalElements();
        return encaissements.stream()
                .map(encaissement -> {
                    EncaissResponseDTO dto = encaissMapper.EncaissTOEncaissResponseDTO(encaissement);
                    dto.setTotalElements(count);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<EncaissResponseDTO> searchEncaissWeek(String produit, String identifiant, String etatEncaissement, String typeIdent, Double montantEnc, String refFacture, PageRequest pageable) {
        Page<Encaissement> encaissements = encaissRepo.searchEncaissThisWeek(produit, identifiant, etatEncaissement, typeIdent, montantEnc, refFacture, pageable);
        long count = encaissements.getTotalElements();
        return encaissements.stream()
                .map(encaissement -> {
                    EncaissResponseDTO dto = encaissMapper.EncaissTOEncaissResponseDTO(encaissement);
                    dto.setTotalElements(count);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<EncaissResponseDTO> searchEncaissMonth(String produit, String identifiant, String etatEncaissement, String typeIdent, Double montantEnc, String refFacture, PageRequest pageable) {
        Page<Encaissement> encaissements = encaissRepo.searchEncaissThisMonth(produit, identifiant, etatEncaissement, typeIdent, montantEnc, refFacture, pageable);
        long count = encaissements.getTotalElements();
        return encaissements.stream()
                .map(encaissement -> {
                    EncaissResponseDTO dto = encaissMapper.EncaissTOEncaissResponseDTO(encaissement);
                    dto.setTotalElements(count);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<EncaissResponseDTO> searchEncaissYear(String produit, String identifiant, String etatEncaissement, String typeIdent, Double montantEnc, String refFacture, PageRequest pageable) {
        Page<Encaissement> encaissements = encaissRepo.searchEncaissThisYear(produit, identifiant, etatEncaissement, typeIdent, montantEnc, refFacture, pageable);
        long count = encaissements.getTotalElements();
        return encaissements.stream()
                .map(encaissement -> {
                    EncaissResponseDTO dto = encaissMapper.EncaissTOEncaissResponseDTO(encaissement);
                    dto.setTotalElements(count);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<EncaissResponseDTO> encaissYear() {
        List<Encaissement> encaissements = encaissRepo.getEncaissementYearly();
        return encaissements.stream()
                .map(encaissement -> encaissMapper.EncaissTOEncaissResponseDTO(encaissement))
                .collect(Collectors.toList());
    }
}
