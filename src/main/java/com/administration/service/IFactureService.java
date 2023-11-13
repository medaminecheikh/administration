package com.administration.service;

import com.administration.dto.EncaissResponseDTO;
import com.administration.dto.FactureResponseDTO;
import com.administration.dto.FactureUpdateDTO;
import com.administration.entity.InfoFacture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface IFactureService {

    InfoFacture addFacture(InfoFacture facture);

    List<InfoFacture> getAll(String identifiant, String ref, int apl, int page, int size);

    List<InfoFacture> getByUser(String idUser);

    void updateFacture(FactureUpdateDTO facture);

    void deleteFacture(String idFacture);

    FactureResponseDTO affectEncaissementToFacture(String encaissementId, String factureId);

    void affectUser(String idUser, String idfac);

    void removeEncaissementFromFacture(String encaissementId, String factureId);

    List<FactureResponseDTO> getAllFactures();

    List<InfoFacture> getAllfacture(String id, String ref, Integer apl);

    List<FactureResponseDTO> searchInfoFactures(
            String produitKeyword, String refFactureKeyword, String compteFacturationKeyword,
            String identifiantKeyword, Double montantMax, Double solde, Pageable pageable);

    double calculatePaymentAmount(InfoFacture facture, Date targetDate);

    List<FactureResponseDTO> getMonthlyFactures();

    List<FactureResponseDTO> getYearlyFactures();

    List<FactureResponseDTO> getFinishedFactures(String produitKeyword, String refFactureKeyword,
                                                 String compteFacturationKeyword, String identifiantKeyword,
                                                 Double montantMax, Double solde, PageRequest pageable);

    List<FactureResponseDTO> searchCoursFactures(String produitKeyword, String refFactureKeyword, String compteFacturationKeyword, String identifiantKeyword, Double montantMax, Double solde, PageRequest pageable);

    List<FactureResponseDTO> searchRetardFactures(String produitKeyword, String refFactureKeyword, String compteFacturationKeyword, String identifiantKeyword, Double montantMax, Double solde);
}


