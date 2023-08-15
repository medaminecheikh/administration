package com.administration.service;

import com.administration.dto.FactureResponseDTO;
import com.administration.dto.FactureUpdateDTO;
import com.administration.entity.InfoFacture;

import java.util.List;

public interface IFactureService {

    InfoFacture addFacture(InfoFacture facture);

    List<InfoFacture> getAll(String identifiant, String ref, int apl, int page, int size);

    List<InfoFacture> getByUser(String idUser);

    void updateFacture(FactureUpdateDTO facture);

    void deleteFacture(String idFacture);

    void affectEncaissementToFacture(String encaissementId, String factureId);

    void affectUser(String idUser, String idfac);

    void removeEncaissementFromFacture(String encaissementId, String factureId);

    List<FactureResponseDTO> getAllFactures();

    List<InfoFacture> getAllfacture(String id, String ref, Integer apl);
}
