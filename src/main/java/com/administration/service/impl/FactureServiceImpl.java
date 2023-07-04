package com.administration.service.impl;

import com.administration.entity.InfoFacture;
import com.administration.entity.OperationEncai;
import com.administration.entity.Utilisateur;
import com.administration.repo.FactureRepo;
import com.administration.repo.OperationRepo;
import com.administration.repo.UtilisateurRepo;
import com.administration.service.IFactureService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class FactureServiceImpl implements IFactureService {
    FactureRepo factureRepo;
    OperationRepo operationRepo;
    UtilisateurRepo utilisateurRepo;
    @Override
    public InfoFacture addFacture(InfoFacture facture) {
        facture.setIdFacture(UUID.randomUUID().toString());
        factureRepo.save(facture);
        return facture;
    }

    @Override
    public List<InfoFacture> getAll(String identifiant,String ref,int apl,int page, int size) {
        Sort sort = Sort.by("datLimPai");
        Page<InfoFacture> factures=factureRepo.findAllFactures(identifiant,ref,apl, PageRequest.of(page, size,sort));
        return factures.getContent();
    }

    @Override
    public List<InfoFacture> getByUser(String idUser) {
        Utilisateur utilisateur=utilisateurRepo.findById(idUser).orElse(null);
        if (utilisateur != null) {
            return utilisateur.getFactures();
        } else {
            return Collections.emptyList();
        }

    }

    @Override
    public void updateFacture(InfoFacture facture) {

    }

    @Override
    public void deleteFacture(String idFacture) {
        factureRepo.deleteById(idFacture);
    }

    @Override
    public void affectOp(String idop, String idfac) {
        InfoFacture facture=factureRepo.findById(idfac).get();
        OperationEncai operationEncai=operationRepo.findById(idop).get();
        operationEncai.setFacture(facture);
        operationRepo.save(operationEncai);
    }

    @Override
    public void affectUser(String idUser, String idfac) {
        Utilisateur utilisateur =utilisateurRepo.findById(idUser).get();
        InfoFacture facture=factureRepo.findById(idfac).get();
        facture.setUser(utilisateur);
        factureRepo.save(facture);
    }
}
