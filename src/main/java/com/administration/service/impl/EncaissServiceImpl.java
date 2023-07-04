package com.administration.service.impl;

import com.administration.entity.*;
import com.administration.repo.*;
import com.administration.service.IEncaissService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class EncaissServiceImpl implements IEncaissService {

    EncaissRepo encaissRepo;
    UtilisateurRepo utilisateurRepo;
    FactureRepo factureRepo;
    CaisseRepo caisseRepo;
    OperationRepo operationRepo;
    EttRepo ettRepo;

    @Override
    public Encaissement addEncaiss(Encaissement encaissement) {
        encaissement.setIdEncaissement(UUID.randomUUID().toString());
        encaissement.setDateEnc(new Date());
        encaissRepo.save(encaissement);
        return encaissement;
    }

    @Override
    public Encaissement getEncaissById(String id) {

        return encaissRepo.findById(id).get();
    }

    @Override
    public List<Encaissement> getEncaissementByFacture(String idFact) {
        InfoFacture facture = factureRepo.findById(idFact).orElse(null);

        if (facture != null) {
            List<OperationEncai> operationEncai = facture.getEncaissements();
            if (operationEncai != null) {
                return operationEncai.stream()
                        .map(OperationEncai::getEncaissement)
                        .collect(Collectors.toList());
            }
        }

        return Collections.emptyList();
    }

    @Override
    public List<Encaissement> getEncaissementByUser(String idUser) {
        Utilisateur utilisateur = utilisateurRepo.findById(idUser).orElse(null);
        List<Encaissement> encaissements = new ArrayList<>();

        if (utilisateur != null) {
            List<InfoFacture> factures = utilisateur.getFactures();
            for (InfoFacture facture : factures) {
                List<OperationEncai> operationEncais = facture.getEncaissements();
                for (OperationEncai operationEncai : operationEncais) {
                    encaissements.add(operationEncai.getEncaissement());
                }
            }
        }

        return encaissements;
    }

    @Override
    public List<Encaissement> getEncaissementByCaisse(String idCaisse) {
        Caisse caisse = caisseRepo.findById(idCaisse).orElse(null);
        if (caisse != null) {
            return caisse.getEncaissements();
        }
        return Collections.emptyList();
    }

    @Override
    public void updateEncaisse(Encaissement dto) {

    }

    @Override
    public void deleteEncaisse(String idEncaiss) {
        encaissRepo.deleteById(idEncaiss);
    }

    @Override
    public void affectEncaisseToOperation(String idEncaiss, String idOp) {
        Encaissement encaissement = encaissRepo.findById(idEncaiss).get();
        OperationEncai operationEncai=operationRepo.findById(idOp).get();
        operationEncai.setEncaissement(encaissement);
        operationRepo.save(operationEncai);
    }

    @Override
    public void affectEncaisseToCaisse(String idEncaiss, String idcai) {
        Encaissement encaissement =encaissRepo.findById(idEncaiss).get();
        Caisse caisse=caisseRepo.findById(idcai).get();
        encaissement.setCaisse(caisse);
        encaissRepo.save(encaissement);
    }



    @Override
    public void affectEncaisseToUser(String idEncaiss, String idUser) {
        Encaissement  encaissement =encaissRepo.findById(idEncaiss).get();
        Utilisateur utilisateur= utilisateurRepo.findById(idUser).get();
        encaissement.setUser(utilisateur);
        encaissRepo.save(encaissement);
    }

    @Override
    public void affectAll(String idEncaiss, String idUser, String idcai) {
        Encaissement  encaissement =encaissRepo.findById(idEncaiss).get();
        Utilisateur utilisateur= utilisateurRepo.findById(idUser).get();
        Caisse caisse=caisseRepo.findById(idcai).get();
        encaissement.setUser(utilisateur);
        encaissement.setCaisse(caisse);
        encaissRepo.save(encaissement);
    }
}
